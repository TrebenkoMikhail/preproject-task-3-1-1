package com.habsida.preproject.controller;

import com.habsida.preproject.model.User;
import com.habsida.preproject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControllerUsers {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping(value = "/")
    private String getUsers(Model model){
        model.addAttribute("users", userServiceImpl.getAllUsers());
        return "users";
    }
    @GetMapping(value = "/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userServiceImpl.getUserById(id));
        return "user";
    }
    @GetMapping(value = "/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }
    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user) {
        userServiceImpl.addUser(user);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user =new User();
        user.setId(id);
        model.addAttribute("user", user);
        return "user-edit";
    }
    @PostMapping("/edit")
    public String editUserSubmit(@ModelAttribute User user, Long id) {
        userServiceImpl.updateUser(user);
        user.setId(id);
        return "redirect:/";
    }
    @GetMapping(value = "/delete/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        User user = userServiceImpl.getUserById(id);
        if (user != null) {
            userServiceImpl.deleteUserById(id);
        }
        return "redirect:/";
    }

}
