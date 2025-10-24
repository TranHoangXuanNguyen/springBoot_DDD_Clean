//Value object
package com.example.demo.domain.user;
import java.util.UUID;
public final class UserId {
    private final UUID value;
    public UserId(UUID value) { this.value = value; }
    public static UserId from(UUID id){ return new UserId(id); }
    public UUID asUUID(){ return value; }
}