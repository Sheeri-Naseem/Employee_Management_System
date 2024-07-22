package org.kane.dao;

import org.kane.entities.Permission;

import java.util.List;

public interface PermissionDao {
    List<Permission> findAllPermissions();

}
