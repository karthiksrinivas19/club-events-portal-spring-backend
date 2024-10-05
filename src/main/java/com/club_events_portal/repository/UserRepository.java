package com.club_events_portal.repository;

import com.club_events_portal.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    // This method will return an Optional<User>
    Optional<User> findByEmailId(String email);
}
