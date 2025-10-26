package com.example.demo.presentation.user;

import com.example.demo.application.command.CreateUserCommand;
import com.example.demo.application.service.CreateUserService;
import com.example.demo.application.service.GetUserByIdService;
import com.example.demo.domain.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST controller expose endpoint /api/users
 * Chỉ làm nhiệm vụ: nhận request → tạo command/query → gọi application service → trả response.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CreateUserService createUserService;
    private final GetUserByIdService getUserByIdService;

    public UserController(CreateUserService createUserService, GetUserByIdService getUserByIdService) {
        this.createUserService = createUserService;
        this.getUserByIdService = getUserByIdService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateUserRequest req) {
        createUserService.handle(new CreateUserCommand(req.name(), req.email()));
        return ResponseEntity.ok("User created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        return ResponseEntity.ok(getUserByIdService.handle(id));
    }
}