package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.sevice.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable long id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        userService.saveUsers(user);
    }

    @PutMapping("/users")
    public void update(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("users/{id}")
    public void delete(@PathVariable long id) {
        userService.deleteByIdUsers(id);
    }
}
