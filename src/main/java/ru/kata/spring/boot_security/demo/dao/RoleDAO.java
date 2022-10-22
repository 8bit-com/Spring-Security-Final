package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> getAllRoles ();
    Role getRole(String userRole);
    List<Role> getRoleById(List<Long> roles);
    void addRole(Role role);
    void removeRole(List<Long> roles);
    void updateRole(Role role);
}
