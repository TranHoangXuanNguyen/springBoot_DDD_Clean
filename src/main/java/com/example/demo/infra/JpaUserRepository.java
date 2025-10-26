package com.example.demo.infra;

import com.example.demo.domain.user.*;
import com.example.demo.infra.persistence.jpa.SpringDataUserRepository;
import com.example.demo.infra.persistence.jpa.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Bridge giữa domain repository interface và JPA repo.
 * Giúp domain không phụ thuộc framework.
 */
@Repository
public class JpaUserRepository implements UserRepository {
    private final SpringDataUserRepository jpaRepo;

    public JpaUserRepository(SpringDataUserRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public Optional<User> findById(UserId id) {
        return jpaRepo.findById(id.asUUID())
                .map(e -> new User(new UserId(e.getId()), new Email(e.getEmail()), e.getName()));
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return jpaRepo.findByEmail(email.value())
                .map(e -> new User(new UserId(e.getId()), new Email(e.getEmail()), e.getName()));
    }

    @Override
    public void save(User user) {
        UserEntity e = new UserEntity();
        e.setId(user.id().asUUID());
        e.setEmail(user.email().value());
        e.setName(user.name());
        jpaRepo.save(e);
    }
}