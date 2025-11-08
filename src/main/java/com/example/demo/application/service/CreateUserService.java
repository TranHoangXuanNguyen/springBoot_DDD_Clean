package com.example.demo.application.service;

import com.example.demo.application.command.CreateUserCommand;
import com.example.demo.domain.user.Email;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserId;
import com.example.demo.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Command handler xử lý logic "tạo user".
 * Domain-Driven Design: service này sử dụng repository domain để lưu entity.
 */
@Service
public class CreateUserService {
    private final UserRepository userRepository;
    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void handle(CreateUserCommand cmd) {
        User user = new User(
                new UserId(UUID.randomUUID()),
                new Email(cmd.email()),
                cmd.name()
        );
        userRepository.save(user);
    }
}
