
package com.club_events_portal.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.club_events_portal.model.Event;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {
}
