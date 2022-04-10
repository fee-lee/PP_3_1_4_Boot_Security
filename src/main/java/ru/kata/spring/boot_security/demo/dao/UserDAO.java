package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO {
    void saveUsers(User user);

    void updateUser(User user);

    List<User> getAllUsers();

    void deleteByIdUsers(long id);

    User findById(long id);

    User getUserByEmail(String email);
}
