package com.example.demo.infra;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRepository;
import com.example.demo.infra.persistence.jpa.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository repository;

    public JpaUserRepository(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = UserEntity.fromDomain(user);
        return repository.save(entity).toDomain();
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id).map(UserEntity::toDomain);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll().stream().map(UserEntity::toDomain).toList();
    }
}
