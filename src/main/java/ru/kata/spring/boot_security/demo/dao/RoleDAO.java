package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDAO {

    Set<Role> getRoleByName(String[] role);

    List<Role> getAllRoles();
}
