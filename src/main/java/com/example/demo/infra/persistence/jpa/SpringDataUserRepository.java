package com.example.demo.infra.persistence.jpa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository auto-implement CRUD.
 * Domain layer không thấy class này — chỉ Infra dùng.
 */
public interface SpringDataUserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
}
