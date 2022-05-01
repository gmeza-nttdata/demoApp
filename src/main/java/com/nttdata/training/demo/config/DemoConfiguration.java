package com.nttdata.training.demo.config;

import com.nttdata.training.demo.repository.MockUserRepository;
import com.nttdata.training.demo.repository.UserRepository;
import com.nttdata.training.demo.service.UserService;
import com.nttdata.training.demo.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfiguration {

    @Bean
    public UserRepository userRepository() {
        return new MockUserRepository();
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

}
