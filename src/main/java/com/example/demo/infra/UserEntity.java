package com.example.demo.infra;

import com.example.demo.domain.User;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    public static UserEntity fromDomain(User user) {
        UserEntity e = new UserEntity();
        e.id = user.getId();
        e.name = user.getName();
        e.email = user.getEmail();
        return e;
    }

    public User toDomain() {
        return new User(id, name, email);
    }
}
