package com.club_events_portal.Controller;

import com.club_events_portal.model.Event;
import com.club_events_portal.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @PostMapping("/create_event")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        // Get authenticated user (assuming user authentication is implemented)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // or however you retrieve the user's details

        // Check if user is found (implement user service logic if needed)
        if (username == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"User not found\"}");
        }

        // Save the event
        eventRepository.save(event);
        return ResponseEntity.ok("{\"message\": \"Event created successfully\"}");
    }
}
