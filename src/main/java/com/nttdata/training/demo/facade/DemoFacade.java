package com.nttdata.training.demo.facade;

import com.nttdata.training.demo.domain.User;
import com.nttdata.training.demo.service.UserService;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class DemoFacade {

    private final UserService userService;

    public DemoFacade(UserService userService) {
        this.userService = userService;
    }

    public Flux<User> get() {
        return userService.getAllUsers();
    }

    public Mono<User> getById(String id) {
        return userService.getUserById(id);
    }

    public Mono<User> create(User user) {
        user.setId(UUID.randomUUID().toString());
        return userService.createUser(user);
    }

    public Mono<User> update(String id, User user) {
        return userService.updateUser(id, user);
    }

    public Mono<Void> delete(String id ) {
        return userService.deleteUser(id);
    }

}
