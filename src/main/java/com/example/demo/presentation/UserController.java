package com.example.demo.presentation;

import com.example.demo.application.UserService;
import com.example.demo.domain.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public User createUser(@RequestBody UserRequest request) {
        return userService.createUser(request.getName(), request.getEmail());
    }
    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
