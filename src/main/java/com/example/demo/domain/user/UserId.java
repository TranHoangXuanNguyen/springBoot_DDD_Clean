//Value object
package com.example.demo.domain.user;
import java.util.UUID;
import java.util.Objects;
public final class UserId {
    private final UUID value;
    public UserId(UUID value) { this.value = value; }
    public static UserId from(UUID id){ return new UserId(id); }
    public UUID asUUID(){ return value; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(value, userId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}