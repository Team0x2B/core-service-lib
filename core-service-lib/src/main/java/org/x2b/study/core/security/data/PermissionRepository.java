package org.x2b.study.core.security.data;

import org.springframework.stereotype.Repository;
import org.x2b.study.core.security.User;

import java.util.Map;
import java.util.Set;

@Repository
public interface PermissionRepository {

    /**
     * Creates a user in the repository with no permissions
     * @param user the user that will be added
     * @return true iff successful
     *
     * @implNote The only stable field of user is the UUID
     */
    boolean createUser(User user);

    /**
     * Remove a user from the repository
     * @param user the user that will be removed
     * @return success
     */
    boolean removeUser(User user);


    /**
     * Update a user in the repository with a new permission
     * @param user The user to be updated
     * @param permission The permission to add
     * @return success
     */
    boolean addPermissionToUser(User user, Permission permission);

    /**
     * Update a user in the repository with a new permission
     * @param user The user to be updated
     * @param permissions The permissions to add
     * @return success
     */
    boolean addPermissionsToUser(User user, Set<Permission> permissions);

    /**
     * Update a user in the repository without a permission
     * @param user The user to be updated
     * @param permission The permission to remove
     * @return success
     */
    boolean removePermissionFromUser(User user, Permission permission);

    /**
     * Update a user in the repository without a permission
     * @param user The user to be updated
     * @param permissions The permissions to remove
     * @return success
     */
    boolean removePermissionsFromUser(User user, Set<Permission> permissions);

    /**
     * Check if a user is present in the repository
     * @param user the user to check
     * @return true if the user exists, false otherwise
     */
    boolean doesUserExist(User user);

    /**
     * Check if a user has a permission
     * @param user The user to read
     * @param permission The permission to check
     * @return true iff the user has the permission
     */
    boolean doesUserHavePermission(User user, Permission permission);

    /**
     * Check if a user has a set of permissions
     * @param user The user to check
     * @param permissions The set of permissions
     * @return A map where each permission in the initial set is a key. Each value is true
     * if the user has the permission and false otherwise.
     * Returns <code>null</code> if the user does not exist in the repository
     */
    Map<Permission, Boolean> doesUserHavePermissions(User user, Set<Permission> permissions);

    /**
     * Check if a user has every permission in a set
     * @param user The user to read
     * @param permissions the set of permissions
     * @return true iff the user has every permission in the set
     */
    boolean doesUserHaveAllPermissions(User user, Set<Permission> permissions);


    /**
     * Get a set containing all of the users permissions
     * @param user The user to read
     * @return The set of permissions or <code>null</code> if the User does not exist in the
     * Repository
     */
    Set<Permission> getAllPermissionsOfUser(User user);
}
