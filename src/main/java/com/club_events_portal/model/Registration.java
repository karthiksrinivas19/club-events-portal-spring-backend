package com.club_events_portal.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "registrations")
public class Registration {
    @Id
    private String id;
    private String userId; // ID of the user
    private String eventId; // ID of the event

    // Constructors
    public Registration() {
    }

    public Registration(String userId, String eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
