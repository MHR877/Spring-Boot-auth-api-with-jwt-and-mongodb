package com.backend.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.backend.backend.model.Admin;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Admin findByEmail(String email);
    Admin findByEmailAndPassword(String email, String password);
}
