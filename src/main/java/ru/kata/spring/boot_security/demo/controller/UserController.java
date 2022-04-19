package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.sevice.RoleService;
import ru.kata.spring.boot_security.demo.sevice.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    //  Output on display all users from ADMIN.
    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    //  Output on display form input data for new user.
    @GetMapping("/admin/newUser")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("getRoles", roles);
        return "newUser";
    }

    //  Sending data user's for add new user.
    @PostMapping("/admin/new")
    public String createUser(@RequestParam("name") String name,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password,
                             @RequestParam("roles") String[] roles) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(roleService.getRoleByName(roles));
        userService.saveUsers(user);
        return "redirect:/admin";
    }

    //  Output on display form input data edit for user.
    @GetMapping("/admin/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("userEdit", userService.findById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit";
}
    //  Save updating user.
    @PutMapping("/admin/{id}")
    public String update(@RequestParam("name") String name,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestParam("roles") String[] roles,
                         @PathVariable("id") long id) {
        User user = userService.findById(id);
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(roleService.getRoleByName(roles));
        System.out.println(user.getPassword());
        userService.updateUser(user);
        return "redirect:/admin";
    }


    @DeleteMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteByIdUsers(id);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String pageUser(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("userPage", user);
        return "user";
    }
}
