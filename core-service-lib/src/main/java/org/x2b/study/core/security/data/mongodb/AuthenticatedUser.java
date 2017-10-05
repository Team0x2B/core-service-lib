package org.x2b.study.core.security.data.mongodb;

import org.springframework.data.annotation.Id;
import org.x2b.study.core.security.User;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AuthenticatedUser implements User{

    @Id
    private final UUID uuid;

    private final Set<String> permissions;

    public AuthenticatedUser(UUID uuid, Set<String> permissions) {
        this.uuid = uuid;
        this.permissions = new HashSet<>(permissions);
    }

    public boolean hasPermission(String permission) {
        return permission.contains(permission);
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public String toString() {
        return String.format("User: %s", uuid);
    }
}
