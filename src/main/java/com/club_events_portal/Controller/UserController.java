package com.club_events_portal.Controller;

import com.club_events_portal.model.User;
import com.club_events_portal.services.UserService;
import org.springframework.http.HttpStatus; // Add this import
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Check if the user object is null
        if (user == null) {
            return ResponseEntity.badRequest().body("User object is null");
        }

        // Log the received user object for debugging
        System.out.println("Received User: " + user);

        // Perform your save logic here, such as saving the user to the database
        userService.registerUser(user);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        // Check if email and password are provided
        if (user.getEmailId() == null || user.getEmailId().isEmpty()) {
            return ResponseEntity.badRequest().body("Email cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Password cannot be empty");
        }
        
        // Validate user credentials
        Optional<User> validatedUser = userService.validateUser(user.getEmailId(), user.getPassword());
        if (validatedUser.isPresent()) {
            // Successful login logic (generate token)
            String token = userService.generateToken(validatedUser.get());
            return ResponseEntity.ok(token); // Return the token in the response
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
