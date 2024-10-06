package com.club_events_portal.services;

import com.club_events_portal.model.User;
import com.club_events_portal.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private static final String SECRET_KEY = "your_secret_keyladdumuthyananavhthara_hihana_sanchyadi_devara_agian_hello_hi_vanakkam_chennai"; // Replace with your actual secret key
    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Method to register a new user
    public User registerUser(User user) {
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
            // Validate password using BCrypt
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user); // Return the user if credentials are valid
            }
        }
        
        return Optional.empty(); // Return empty if credentials are invalid
    }

    // Method to generate JWT token
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 1 day expiration
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes())
                .compact();
    }

}
