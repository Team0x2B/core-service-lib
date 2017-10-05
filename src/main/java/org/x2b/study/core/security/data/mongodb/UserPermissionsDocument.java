package org.x2b.study.core.security.data.mongodb;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UserPermissionsDocument {

    private final UUID userId;
    private final Set<String> permissions;


    public UserPermissionsDocument(UUID userId, Set<String> permissions) {
        this.userId = userId;
        this.permissions = new HashSet<>(permissions);
    }

    public boolean hasPermission(String permission) {
        return permission.contains(permission);
    }

    public UUID getUserId() {
        return userId;
    }
}
