package com.nttdata.training.demo.config;

import com.azure.storage.blob.BlobServiceAsyncClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.nttdata.training.demo.repository.MockUserRepository;
import com.nttdata.training.demo.repository.UserRepository;
import com.nttdata.training.demo.service.UserService;
import com.nttdata.training.demo.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfiguration {

    private static final String AZURE_STORAGE_CONNECTION_STRING = "AZURE_STORAGE_CONNECTION_STRING";

    @Bean
    public UserRepository userRepository() {
        return new MockUserRepository();
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    public BlobServiceClient blobServiceClient() {
        return new BlobServiceClientBuilder().connectionString(System.getenv(AZURE_STORAGE_CONNECTION_STRING)).buildClient();
    }

    @Bean
    public BlobServiceAsyncClient blobServiceAsyncClient() {
        return new BlobServiceClientBuilder().connectionString(System.getenv(AZURE_STORAGE_CONNECTION_STRING)).buildAsyncClient();
    }


}
