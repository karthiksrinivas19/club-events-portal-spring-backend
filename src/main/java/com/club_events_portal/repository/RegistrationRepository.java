package com.club_events_portal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.club_events_portal.model.Registration;

@Repository
public interface RegistrationRepository extends MongoRepository<Registration, String> {
    Registration findByUserIdAndEventId(String userId, String eventId);
}
