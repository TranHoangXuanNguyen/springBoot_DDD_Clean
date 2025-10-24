package com.example.demo.domain.user;
/**
 * Domain entity đại diện cho người dùng trong hệ thống.
 * Đây là phần core — không phụ thuộc framework nào (pure Java).
 */
public class User {
    private final UserId id;
    private Email email;
    private String name;

    public User(UserId id, Email email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    // Getter dạng domain
    public UserId id() { return id; }
    public Email email() { return email; }
    public String name() { return name; }



    public void changeEmail(Email newEmail) {
        this.email = newEmail;
    }
}
