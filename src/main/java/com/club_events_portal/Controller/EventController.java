package com.club_events_portal.Controller;

import com.club_events_portal.model.Event;
import com.club_events_portal.model.Registration;
import com.club_events_portal.repository.EventRepository;
import com.club_events_portal.repository.RegistrationRepository;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    private RegistrationRepository registrationRepository;

    @PostMapping("/create_event")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        // Get authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // or however you retrieve the user's details

        // Check if user is found
        if (username == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"User not found\"}");
        }

        // Save the event
        eventRepository.save(event);
        return ResponseEntity.ok("{\"message\": \"Event created successfully\"}");
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getEvents() {
        // Get authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Check if user is found
        if (username == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Fetch events
        List<Event> events = eventRepository.findAll();

        return ResponseEntity.ok(events);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<?> getEventById(@PathVariable String id) {
        Optional<Event> event = eventRepository.findById(id);
        
        if (event.isPresent()) {
            return ResponseEntity.ok(event.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Event not found\"}");
        }
    }

    @GetMapping("/register_event/{eventId}")
    public ResponseEntity<?> registerForEvent(@PathVariable String eventId) {
        // Get authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName(); 

        // Check if the user is already registered
        Registration existingRegistration = registrationRepository.findByUserIdAndEventId(userId, eventId); // Use the instance
        if (existingRegistration != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Already registered\"}");
        }

        // Register the user for the event
        Registration registration = new Registration(userId, eventId);
        registrationRepository.save(registration); // Use the instance
        return ResponseEntity.ok("{\"message\": \"Registered successfully\"}");
    }
}
