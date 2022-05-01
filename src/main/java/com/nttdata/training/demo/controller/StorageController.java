package com.nttdata.training.demo.controller;

import com.azure.core.http.rest.PagedFlux;
import com.azure.storage.blob.models.BlobItem;
import com.azure.storage.blob.models.BlockBlobItem;
import com.nttdata.training.demo.dto.web.BlobContainerWebDto;
import com.nttdata.training.demo.facade.StorageFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;

@Slf4j
@RestController
@RequestMapping("storage-account")
public class StorageController {

    private final StorageFacade storageFacade;

    public StorageController(StorageFacade storageFacade) {
        this.storageFacade = storageFacade;
    }

    @PostMapping(value = "/container", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<BlobContainerWebDto> createRandomContainer() {
        log.info("createRandomContainer");
        return storageFacade.createRandomContainer();
    }

    @GetMapping(value = "/container/{container}/blob")
    public PagedFlux<BlobItem> getBlobListInContainer(@PathVariable(value = "container") String container) {
        log.info("List blobs in container: {}", container);
        return storageFacade.getBlobs(container);
    }

    @GetMapping(value = "/container/{container}/blob/{blobName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Flux<ByteBuffer> downloadBlob(@PathVariable("blobName") String blobName, @PathVariable("container") String container) {
        log.info("Get Blob: container={}, blobName={}", container, blobName);
        return storageFacade.getBlob(container, blobName).download();
    }

    @PostMapping(value = "/container/{container}/blob", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Mono<BlockBlobItem> upload(@RequestPart(value = "name") String name, @RequestPart(value = "file") FilePart file, @PathVariable("container") String container) {
        log.info("Upload: file={},name={},container={}", file.filename(), name, container);
        return storageFacade.uploadBlob(file, name, container);
    }

    @DeleteMapping(value = "/container/{container}/blob/{blobName}")
    public Mono<Void> delete(@PathVariable("container") String container, @PathVariable("blobName") String blobName) {
        log.info("Delete: container={}, blobName={}", container, blobName);
        return storageFacade.deleteBlob(container, blobName);
    }

}
