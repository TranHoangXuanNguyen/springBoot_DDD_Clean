package com.example.demo.infra.persistence.jpa;

import com.example.demo.domain.user.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public User save(User user) {
        UserEntity e = new UserEntity();
        e.setId(user.id().asUUID());
        e.setEmail(user.email().value());
        e.setName(user.name());
        UserEntity saved = jpaRepo.save(e);

        return toDomain(saved);
    }
    @Override
    public List<User> findAll() {
        return jpaRepo.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }


    private User toDomain(UserEntity entity) {
        return new User(
                new UserId(entity.getId()),
                new Email(entity.getEmail()),
                entity.getName()
        );
    }

    private UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.id().asUUID());
        entity.setEmail(user.email().value());
        entity.setName(user.name());
        return entity;
    }

}