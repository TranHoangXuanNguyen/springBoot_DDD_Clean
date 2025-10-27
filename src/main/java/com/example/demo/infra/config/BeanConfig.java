package com.example.demo.infra.config;

import com.example.demo.application.service.CreateUserService;
import com.example.demo.application.service.GetUserService;
import com.example.demo.domain.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public CreateUserService createUserService(UserRepository userRepository) {
        return new CreateUserService(userRepository);
    }
    @Bean
    public GetUserService getUserService(UserRepository userRepository) {
        return new GetUserService(userRepository);
    }
}
