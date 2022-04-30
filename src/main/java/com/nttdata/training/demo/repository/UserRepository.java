package com.nttdata.training.demo.repository;

import com.nttdata.training.demo.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Flux<User> findAll();

    Mono<User> findById(String id);

    Mono<User> create(User user);

    Mono<User> update(String id, User user);

    Mono<Void> delete(String id);

}
