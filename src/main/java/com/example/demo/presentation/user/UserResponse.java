package com.example.demo.presentation.user;

import com.example.demo.domain.user.User;

public class UserResponse {
    private final String id;
    private final String email;
    private final String name;

    public UserResponse(String id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    // static factory method để convert từ domain sang DTO
    public static UserResponse fromDomain(User user) {
        return new UserResponse(
                user.id().asUUID().toString(),
                user.email().value(),
                user.name()
        );
    }

    // Getters để Jackson serialize
    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getName() { return name; }
}
