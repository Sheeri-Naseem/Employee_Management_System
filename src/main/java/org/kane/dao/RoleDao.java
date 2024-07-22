package org.kane.dao;

import org.kane.entities.Permission;
import org.kane.entities.Role;

import java.util.List;

public interface RoleDao {

    List<Role> findAllRoles();

    List<Permission> findPermissionsByRole(Role role);

}
