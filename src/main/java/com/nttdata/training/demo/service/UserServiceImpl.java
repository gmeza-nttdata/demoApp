package com.nttdata.training.demo.service;

import com.nttdata.training.demo.domain.User;
import com.nttdata.training.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<User> createUser(User user) {
        return userRepository.create(user);
    }

    @Override
    public Mono<User> updateUser(String id, User user) {
        return userRepository.update(id, user);
    }

    @Override
    public Mono<Void> deleteUser(String id) {
        return userRepository.delete(id);
    }
}
