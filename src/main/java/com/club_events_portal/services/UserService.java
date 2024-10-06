package com.club_events_portal.services;

import com.club_events_portal.model.User;
import com.club_events_portal.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private static final String SECRET_KEY = "your_secret_key"; // Replace with your actual secret key
    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

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

    // Method to generate JWT token
    public String generateToken(User user) {
        // Check the secret key
        String secretKey = "your-secret-key-123456-very-secret===kindly-keep-it-safe-plaese-ok-fine"; // Ensure it does not have illegal characters

        // Ensure proper Base64 encoding and signing algorithm
        return Jwts.builder()
                .setSubject(user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 864_000_000)) // 10 days
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()) // Ensure correct key handling
                .compact();
    }

}
