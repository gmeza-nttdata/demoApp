package com.nttdata.training.demo.controller;

import com.nttdata.training.demo.dto.CosmosContainerWebDto;
import com.nttdata.training.demo.dto.CosmosDbWebDto;
import com.nttdata.training.demo.dto.CosmosItemWebDto;
import com.nttdata.training.demo.facade.CosmosFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@Slf4j
@RestController
@RequestMapping("cosmos")
public class CosmosController {

    private final CosmosFacade cosmosFacade;

    public CosmosController(CosmosFacade cosmosFacade) {
        this.cosmosFacade = cosmosFacade;
    }

    @PostMapping( value = {"", "/"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CosmosDbWebDto> createDb(@RequestBody CosmosDbWebDto cosmosDbWebDto) {
        return cosmosFacade.createDb(cosmosDbWebDto.getId())
                .map(cosmosAsyncDatabase -> {
                    BeanUtils.copyProperties(cosmosDbWebDto, cosmosAsyncDatabase);
                    return cosmosDbWebDto;
                });
    }

    @GetMapping(value = "/{id}")
    public Mono<String> get(@PathVariable("id") String id) {
        return cosmosFacade.getDb(id);

    }

    @DeleteMapping(value = "/{id}")
    public Mono<String> deleteDb(@PathVariable("id") String id) {
        return cosmosFacade.deleteDb(id);
    }

    @PostMapping(value = "/{id}/container", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> createContainer(@PathVariable("id") String id, @RequestBody CosmosContainerWebDto container) {
        return cosmosFacade.createContainer(id, container.getContainer());
    }

    @GetMapping(value = "/{id}/container/{container}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> getContainerById(@PathVariable("id") String id, @PathVariable("container") String container) {
        return cosmosFacade.getContainerById(id, container);
    }

    @DeleteMapping(value = "/{id}/container/{container}")
    public Mono<String> deleteContainerById(@PathVariable("id") String id, @PathVariable("container") String container) {
        return cosmosFacade.deleteContainer(id, container);
    }

    @PostMapping(value = "/{id}/container/{container}/item")
    public Mono<CosmosItemWebDto> createItem(@PathVariable("id") String id, @PathVariable("container") String container,
                                   @RequestBody CosmosItemWebDto dto) {
        return cosmosFacade.createItem(id, container, dto);
    }

}
