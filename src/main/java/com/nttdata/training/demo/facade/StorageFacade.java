package com.nttdata.training.demo.facade;

import com.azure.core.http.rest.PagedFlux;
import com.azure.core.util.BinaryData;
import com.azure.storage.blob.*;
import com.azure.storage.blob.models.BlobItem;
import com.azure.storage.blob.models.BlockBlobItem;
import com.nttdata.training.demo.dto.web.BlobContainerWebDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class StorageFacade {

    private final BlobServiceAsyncClient blobServiceAsyncClient;
    private final BlobServiceClient blobServiceClient;

    public StorageFacade(BlobServiceAsyncClient blobServiceAsyncClient, BlobServiceClient blobServiceClient) {
        this.blobServiceAsyncClient = blobServiceAsyncClient;
        this.blobServiceClient = blobServiceClient;
    }

    public Mono<BlobContainerWebDto> createRandomContainer() {
        return Mono.just("quickstartblobs" + java.util.UUID.randomUUID())
                .flatMap(blobServiceAsyncClient::createBlobContainer)
                .map(this::toBlobContainerWebDto);
    }

    private BlobContainerWebDto toBlobContainerWebDto(BlobContainerAsyncClient containerAsyncClient) {
        var container = new BlobContainerWebDto();

        BeanUtils.copyProperties(containerAsyncClient, container);

        return container;
    }

    public PagedFlux<BlobItem> getBlobs(String container) {
        return blobServiceAsyncClient.getBlobContainerAsyncClient(container).listBlobs();
    }

    public BlobAsyncClient getBlob(String container, String blobName) {
        return blobServiceAsyncClient.getBlobContainerAsyncClient(container).getBlobAsyncClient(blobName);
    }

    public Mono<BlockBlobItem> uploadBlob(MultipartFile file, String name, String container) {

        var blobClient = blobServiceAsyncClient.getBlobContainerAsyncClient(container).getBlobAsyncClient(name);

        return Mono.just(file)
                .map(this::getBytes)
                .flatMap(Mono::fromFuture)
                .map(BinaryData::fromBytes)
                .flatMap(blobClient::upload);
    }

    public Mono<BlockBlobItem> uploadBlob(FilePart file, String name, String container) {

        var blobClient = blobServiceAsyncClient.getBlobContainerAsyncClient(container).getBlobAsyncClient(name);

        return Mono.just(file.content())
                .flatMap(this::mergeDataBuffers)
                .map(BinaryData::fromBytes)
                .flatMap(blobClient::upload);
    }

    @Async
    CompletableFuture<byte[]> getBytes(MultipartFile file) {
        try {
            return CompletableFuture.completedFuture(file.getBytes());
        } catch (IOException e) {
            log.info("IOException, {}", e.getMessage());
            e.printStackTrace();
            return CompletableFuture.completedFuture(null);
        }
    }

    Mono<byte[]> mergeDataBuffers(Flux<DataBuffer> dataBufferFlux) {
        return DataBufferUtils.join(dataBufferFlux)
                .map(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    DataBufferUtils.release(dataBuffer);
                    return bytes;
                });
    }

    public Mono<Void> deleteBlob(String container, String blobName) {

        return blobServiceAsyncClient.getBlobContainerAsyncClient(container)
                .getBlobAsyncClient(blobName).delete();
    }
}

