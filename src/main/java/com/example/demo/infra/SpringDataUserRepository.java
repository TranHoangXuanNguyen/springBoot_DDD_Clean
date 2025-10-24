package com.example.demo.infra;

import com.example.demo.infra.persistence.jpa.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
}
