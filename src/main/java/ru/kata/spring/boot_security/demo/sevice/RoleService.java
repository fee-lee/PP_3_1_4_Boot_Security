package ru.kata.spring.boot_security.demo.sevice;

import ru.kata.spring.boot_security.demo.model.Role;

public interface RoleService {

    void addRole(Role role);

    Role getRoleByName(Role role);
}
