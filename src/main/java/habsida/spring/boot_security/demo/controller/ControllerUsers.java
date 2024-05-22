package habsida.spring.boot_security.demo.controller;

import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerUsers {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping(value = "/admin")
    private String getUsers(Model model){
        model.addAttribute("users", userServiceImpl.getAllUsers());
        return "admin";
    }
    @GetMapping(value = "/user")
    public String userHome(Model model) {
        User user = userServiceImpl.getCurrentUser();
        model.addAttribute("user", user);
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
        return "redirect:/admin";
    }
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userServiceImpl.getUserById(id);
        model.addAttribute("user", user);
        return "user-edit";
    }
    @PostMapping("/edit")
    public String editUserSubmit(@ModelAttribute User user) {
        userServiceImpl.updateUser(user);
        return "redirect:/admin";
    }
    @GetMapping(value = "/delete/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        User user = userServiceImpl.getUserById(id);
        if (user != null) {
            userServiceImpl.deleteUserById(id);
        }
        return "redirect:/admin";
    }

}
