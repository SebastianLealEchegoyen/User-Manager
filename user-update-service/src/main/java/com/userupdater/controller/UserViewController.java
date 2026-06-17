package com.userupdater.controller;

import com.userupdater.user.DuplicateEmailException;
import com.userupdater.user.UpdateUserRequest;
import com.userupdater.user.User;
import com.userupdater.user.UserNotFoundException;
import com.userupdater.user.UserRepository;
import com.userupdater.user.UserService;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserViewController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserViewController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user-list";
    }

    @GetMapping("/users/{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("/users/{id}/edit")
    public String updateUser(@PathVariable UUID id,
                             @RequestParam String email,
                             @RequestParam String fullName,
                             Model model) {
        try {
            userService.updateUser(id, new UpdateUserRequest(email, fullName));
            model.addAttribute("success", "User updated successfully!");
        } catch (DuplicateEmailException e) {
            model.addAttribute("error", e.getMessage());
        } catch (UserNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        model.addAttribute("user", user);
        return "edit-user";
    }
}
