package com.nttdata.training.demo.repository;

import com.nttdata.training.demo.domain.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
public class MockUserRepository implements UserRepository {

    private static final List<User> users = new ArrayList<>();

    @Override
    public Flux<User> findAll() {
        return Flux.fromIterable(users);
    }

    @Override
    public Mono<User> findById(String id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .map(Mono::just)
                .findFirst()
                .orElse(Mono.empty());
    }

    @Override
    public Mono<User> create(User user) {
        users.add(user);
        return Mono.just(user);
    }

    @Override
    public Mono<User> update(String id, User user) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .map(u -> u.updateUser(user))
                .map(Mono::just)
                .findFirst()
                .orElse(Mono.empty());
    }

    @Override
    public Mono<Void> delete(String id) {
        users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .ifPresent(users::remove);
        return Mono.empty();
    }

}
