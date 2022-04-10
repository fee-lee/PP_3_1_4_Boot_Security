package ru.kata.spring.boot_security.demo.sevice;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService extends UserDetailsService {

    void saveUsers(User user);

    void updateUser(User user);

    List<User> getAllUsers();

    void deleteByIdUsers(long id);

    User findById(long id);

    User getByEmail(String eMail);
}
