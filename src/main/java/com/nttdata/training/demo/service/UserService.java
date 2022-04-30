package com.nttdata.training.demo.service;

import com.nttdata.training.demo.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Flux<User> getAllUsers();

    Mono<User> getUserById(String id);

    Mono<User> createUser(User user);

    Mono<User> updateUser(String id, User user);

    Mono<Void> deleteUser(String id);

}
