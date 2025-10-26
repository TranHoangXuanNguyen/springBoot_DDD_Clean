package com.example.demo.presentation.user;

/**
 * DTO chứa dữ liệu gửi lên từ client khi tạo user.
 */
public class CreateUserRequest {
    private String name;
    private String email;

    public String name() { return name; }
    public String email() { return email; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
}
