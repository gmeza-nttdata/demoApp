package com.nttdata.training.demo.controller;

import com.nttdata.training.demo.domain.User;
import com.nttdata.training.demo.facade.DemoFacade;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/demo/user")
public class UserDemoController {

    private final DemoFacade demoFacade;

    public UserDemoController(DemoFacade demoFacade) {
        this.demoFacade = demoFacade;
    }

    @Operation(summary = "2.1 Get Users")
    @GetMapping
    public Flux<User> get() {
        log.info("Get all users");
        return demoFacade.get();
    }

    @Operation(summary = "2.2 Get User by Id")
    @GetMapping("/{id}")
    public Mono<User> get(@PathVariable("id") String id) {
        log.info("Get user by id");
        return demoFacade.getById(id);
    }

    @Operation(summary = "2.3 Create User")
    @PostMapping
    public Mono<User> post(@RequestBody User user) {
        log.info("Create user");
        return demoFacade.create(user);
    }

    @Operation(summary = "2.4 Update User by Id")
    @PutMapping("/{id}")
    public Mono<User> put(@PathVariable("id") String id, @RequestBody User user) {
        log.info("Update user");
        return demoFacade.update(id, user);
    }

    @Operation(summary = "2.5 Delete User by Id")
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        log.info("Delete user by id");
        return demoFacade.delete(id);
    }

}
