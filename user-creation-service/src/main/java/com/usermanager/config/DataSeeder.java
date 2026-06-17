package com.usermanager.config;

import com.usermanager.user.User;
import com.usermanager.user.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements ApplicationRunner {

    private final UserRepository userRepository;

    public DataSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        seedUser("john.doe@example.com", "John Doe");
        seedUser("jane.smith@example.com", "Jane Smith");
        seedUser("bob.wilson@example.com", "Bob Wilson");
    }

    private void seedUser(String email, String fullName) {
        if (!userRepository.existsByEmail(email)) {
            User user = new User();
            user.setEmail(email);
            user.setFullName(fullName);
            userRepository.save(user);
        }
    }
}
