package com.backend.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.backend.backend.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
}
