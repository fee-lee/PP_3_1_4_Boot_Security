package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.sevice.RoleService;
import ru.kata.spring.boot_security.demo.sevice.UserService;

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
    public String getAllUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("users", userService.findById(user.getId()));
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "users";
    }

    //  Sending data user's for add new user.
    @PostMapping("/admin/new")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "rolesN") String[] roles) {
        user.setRoles(roleService.getRoleByName(roles));
        userService.saveUsers(user);
        return "redirect:/admin";
    }

    //  Output on display form input data edit for user.
    @GetMapping("/admin/edit/{id}")
    public String edit(@ModelAttribute("user") User user,
                        @PathVariable("id") long id,
                       Model model,
                       @RequestParam(value = "rolesE") String[] roles) {
        user.setRoles(roleService.getRoleByName(roles));
        model.addAttribute("userEdit", userService.findById(id));
        model.addAttribute("rolesE", roleService.getAllRoles());
        return "users";
}
    //  Save updating user.
    @PutMapping("/admin/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam("rolesE") String[] roles) {
        user.setRoles(roleService.getRoleByName(roles));
        userService.updateUser(user);
        return "redirect:/admin";
    }

//    Delete user
    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteByIdUsers(id);
        return "redirect:/admin";
    }

//  Show ordinal user
    @GetMapping("/user")
    public String pageUser(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("userPage", userService.findById(user.getId()));
        System.out.println(user);
        model.addAttribute("rolesU" , userService.findById(user.getId()).getRoles());
        System.out.println(user.getRoles());
        return "user";
    }
}
