package com.backend.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.backend.backend.model.Club;

public interface ClubRepository extends MongoRepository<Club, String> {
    Club findByEmail(String email);
    Club findByEmailAndPassword(String email, String password);
}
