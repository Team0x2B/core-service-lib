package org.x2b.study.core.security.data;

import org.x2b.study.core.security.User;

import java.util.Collection;

public interface PermissionRepository {

    boolean isUserPresent(User user);

    boolean addUser(User user);

    boolean addPermissionToUser(User user, Permission permission);

    boolean addPermissionsToUser(User user, Collection<Permission> permissions);

    boolean removePermissionFromUser(User user, Permission permission);
}
