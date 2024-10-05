package com.club_events_portal.services;

import com.club_events_portal.model.User;
import com.club_events_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method to register a new user
    public User registerUser(User user) {
        // You might want to add validation or check for existing users here
        return userRepository.save(user);
    }

    // Method to find a user by email
    public Optional<User> findByEmailId(String email) {
        return userRepository.findByEmailId(email);
    }

    // Method to validate user credentials
    public Optional<User> validateUser(String emailId, String password) {
        Optional<User> optionalUser = userRepository.findByEmailId(emailId);
        
        // If the user is found, check the password
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Validate password
            if (user.getPassword().equals(password)) {
                return Optional.of(user); // Return the user if credentials are valid
            }
        }
        
        return Optional.empty(); // Return empty if credentials are invalid
    }
}
