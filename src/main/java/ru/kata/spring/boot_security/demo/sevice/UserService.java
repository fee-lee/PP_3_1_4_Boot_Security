package ru.kata.spring.boot_security.demo.sevice;

import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {

    void saveUsers(User user);

    void updateUser(User user);

    List<User> getAllUsers();

    void deleteByIdUsers(long id);

    User findById(long id);

    User getByEmail(String eMail);
}
