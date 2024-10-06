
package com.club_events_portal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "events")
public class Event {
    @Id
    private String id;
    private String name;
    private String description;
    private String date;
    private String time;
    private String venue;
    private List<String> departments;
    private List<String> years;
    private String organisation;
    private String poster;

    // Constructors
    public Event() {
    }

    public Event(String name, String description, String date, String time, String venue, List<String> departments, List<String> years, String organisation, String poster) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.departments = departments;
        this.years = years;
        this.organisation = organisation;
        this.poster = poster;
    }

    // Getters and setters
    // (You can use Lombok annotations to generate these if preferred)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public List<String> getDepartments() {
        return departments;
    }

    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }

    public List<String> getYears() {
        return years;
    }

    public void setYears(List<String> years) {
        this.years = years;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
