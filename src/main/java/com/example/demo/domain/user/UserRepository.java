package com.example.demo.domain.user;

import java.util.Optional;
import java.util.List;

public interface UserRepository {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(UserId id);
    Optional<User> findByEmail(Email email);
}