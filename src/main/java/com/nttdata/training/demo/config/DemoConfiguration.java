package com.nttdata.training.demo.config;

import com.azure.cosmos.CosmosAsyncClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.nttdata.training.demo.repository.MockUserRepository;
import com.nttdata.training.demo.repository.UserRepository;
import com.nttdata.training.demo.service.UserService;
import com.nttdata.training.demo.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfiguration {

    private static final String COSMOS_SERVICE_ENDPOINT = "COSMOS_SERVICE_ENDPOINT";
    private static final String COSMOS_KEY = "COSMOS_KEY";

    @Bean
    public UserRepository userRepository() {
        return new MockUserRepository();
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    public CosmosAsyncClient cosmosAsyncClient() {
        return new CosmosClientBuilder()
                .endpoint(System.getenv(COSMOS_SERVICE_ENDPOINT))
                .key(System.getenv(COSMOS_KEY))
                .buildAsyncClient();
    }


}
