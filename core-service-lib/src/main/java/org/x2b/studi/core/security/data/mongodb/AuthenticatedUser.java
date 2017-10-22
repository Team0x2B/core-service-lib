package org.x2b.studi.core.security.data.mongodb;

import org.springframework.data.annotation.Id;
import org.x2b.studi.core.security.User;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AuthenticatedUser implements User {

    @Id
    private final UUID id;

    private final Set<String> permissions;

    public AuthenticatedUser(UUID id, Set<String> permissions) {
        this.id = id;
        this.permissions = new HashSet<>(permissions);
    }

    public boolean hasPermission(String permission) {
        return permission.contains(permission);
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    @Override
    public UUID getUUID() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("User: %s", id);
    }

    @Override
    public boolean equals(Object other) {
        if (other != null && other instanceof AuthenticatedUser) {
            return ((AuthenticatedUser) other).id.equals(id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}