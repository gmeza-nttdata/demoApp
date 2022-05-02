package com.nttdata.training.demo.facade;

import com.azure.cosmos.CosmosAsyncClient;
import com.azure.cosmos.CosmosAsyncDatabase;
import com.azure.cosmos.models.CosmosItemResponse;
import com.nttdata.training.demo.dto.CosmosItemWebDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Component
public class CosmosFacade {

    private final CosmosAsyncClient cosmosAsyncClient;

    public CosmosFacade(CosmosAsyncClient cosmosAsyncClient) {
        this.cosmosAsyncClient = cosmosAsyncClient;
    }


    public Mono<CosmosAsyncDatabase> createDb(String databaseId) {
        return cosmosAsyncClient.createDatabaseIfNotExists(databaseId)
                // TIP: Our APIs are Reactor Core based, so try to chain your calls
                .map(databaseResponse -> cosmosAsyncClient.getDatabase(databaseResponse.getProperties().getId()));
    }

    public Mono<String> deleteDb(String id) {
        return cosmosAsyncClient.getDatabase(id).delete().map(Object::toString);
    }

    public Mono<String> getDb(String id) {
        return cosmosAsyncClient.getDatabase(id).read().map(Objects::toString);
    }

    public Mono<String> createContainer(String id, String containerId) {
        return cosmosAsyncClient.createDatabaseIfNotExists(id)
                .flatMap(databaseResponse -> {
                    String databaseId = databaseResponse.getProperties().getId();
                    return cosmosAsyncClient.getDatabase(databaseId)
                            // Create Container
                            .createContainerIfNotExists(containerId, "/id")
                            .map(containerResponse -> cosmosAsyncClient.getDatabase(databaseId)
                                    .getContainer(containerResponse.getProperties().getId()));
                })
                .map(Object::toString);
    }

    public Mono<String> getContainerById(String id, String container) {
        return cosmosAsyncClient.getDatabase(id)
                .getContainer(container)
                .read()
                .map(Object::toString);
    }

    public Mono<String> deleteContainer(String id, String container) {
        return cosmosAsyncClient.getDatabase(id)
                .getContainer(container)
                .delete()
                .map(Object::toString);
    }

    public Mono<CosmosItemWebDto> createItem(String id, String container, CosmosItemWebDto dto) {
        return cosmosAsyncClient.getDatabase(id)
                .getContainer(container)
                .createItem(dto)
                .map(CosmosItemResponse::getItem);
    }
}
