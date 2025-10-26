package com.example.demo.infra.persistence.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

/**
 * JPA Entity — phản ánh User trong DB.
 * (Infra layer implement repository dựa trên entity này)
 */
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private UUID id;
    private String email;
    private String name;

    // Getters & Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
