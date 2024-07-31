package com.backend.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.backend.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
    List<User> findByStateFalse();
    List<User> findByStateTrue();
    List<User> findByState(boolean state);
    List<User> findByFirstNameContainingOrLastNameContainingOrEmailContaining(String firstName, String lastName, String email);
    
    Page<User> findByUsernameAndEmail(String username, String email, Pageable pageable);
    Page<User> findByUsernameAndState(String username, Boolean state, Pageable pageable);
    Page<User> findByEmailAndState(String email, Boolean state, Pageable pageable);
    
    Page<User> findByUsername(String username, Pageable pageable);
    Page<User> findByEmail(String email, Pageable pageable);
    Page<User> findByState(Boolean state, Pageable pageable);
    
    Page<User> findByUsernameContaining(String username, Pageable pageable);
    Page<User> findByEmailContaining(String email, Pageable pageable);
    Page<User> findByUsernameContainingAndEmailContainingAndState(String username, String email, Boolean state, Pageable pageable);
    Page<User> findByUsernameContainingAndEmailContaining(String username, String email, Pageable pageable);
    Page<User> findByUsernameContainingAndState(String username, Boolean state, Pageable pageable);
    Page<User> findByEmailContainingAndState(String email, Boolean state, Pageable pageable);
}
