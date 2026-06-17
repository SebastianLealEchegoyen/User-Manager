package com.usercheck.controller;

import com.usercheck.user.User;
import com.usercheck.user.UserNotFoundException;
import com.usercheck.user.UserRepository;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserViewController {

    private final UserRepository userRepository;

    public UserViewController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user-list";
    }

    @GetMapping("/view/{id}")
    public String viewUser(@PathVariable UUID id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        model.addAttribute("user", user);
        return "user-detail";
    }

    @GetMapping("/search")
    public String searchById(@RequestParam(required = false) String id, Model model) {
        if (id != null && !id.isBlank()) {
            try {
                UUID uuid = UUID.fromString(id.trim());
                userRepository.findById(uuid).ifPresentOrElse(
                        user -> model.addAttribute("user", user),
                        () -> model.addAttribute("error", "No user found with id: " + id));
            } catch (IllegalArgumentException e) {
                model.addAttribute("error", "Invalid UUID format");
            }
        }
        return "search";
    }
}
