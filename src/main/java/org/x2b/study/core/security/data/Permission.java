package org.x2b.study.core.security.data;

public class Permission {

    private final String permission;

    public Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    @Override
    public boolean equals(Object other) { //this could thrown a null ptr if permission is null
        if (other != null && other instanceof Permission) {
            return this.permission.equals(((Permission) other).permission);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return permission.hashCode();
    }
}
