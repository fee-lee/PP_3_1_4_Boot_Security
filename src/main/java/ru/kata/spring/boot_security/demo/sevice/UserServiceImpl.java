package ru.kata.spring.boot_security.demo.sevice;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;


    @Lazy
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void saveUsers(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveUsers(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        if (user.getPassword().equals("")) {
            user.setPassword(userDAO.findById(user.getId()).getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDAO.updateUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Transactional
    @Override
    public void deleteByIdUsers(long id) {
        userDAO.deleteByIdUsers(id);
    }

    @Override
    public User findById(long id) {
        return userDAO.findById(id);
    }

    @Override
    public User getByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Not found " + email);
        }
        return user;
//                userDAO.getUserByEmail(email);
    }
}
