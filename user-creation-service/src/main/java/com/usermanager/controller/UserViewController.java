package com.usermanager.controller;

import com.usermanager.user.CreateUserRequest;
import com.usermanager.user.DuplicateEmailException;
import com.usermanager.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserViewController {

    private final UserService userService;

    public UserViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/create")
    public String showCreateForm() {
        return "create-user";
    }

    @PostMapping("/users/create")
    public String createUser(@RequestParam String email,
                             @RequestParam String fullName,
                             Model model) {
        try {
            userService.createUser(new CreateUserRequest(email, fullName));
            model.addAttribute("success", "User created successfully!");
        } catch (DuplicateEmailException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
        }
        return "create-user";
    }
}
