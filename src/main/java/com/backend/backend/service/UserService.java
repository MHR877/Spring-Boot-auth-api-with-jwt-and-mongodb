package com.backend.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.backend.model.User;
import com.backend.backend.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public List<User> getUsersWithFalseStatus() {
        return userRepository.findByStateFalse();
    }

    public List<User> getUsersWithTrueStatus() {
        return userRepository.findByStateTrue();
    }

    public List<User> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.getContent();
    }

    public List<User> searchUsers(String username, String email, Boolean state, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        
        if (username != null && email != null && state != null) {
            return userRepository.findByUsernameContainingAndEmailContainingAndState(username, email, state, pageable)
                    .getContent();
        } else if (username != null && email != null) {
            return userRepository.findByUsernameContainingAndEmailContaining(username, email, pageable).getContent();
        } else if (username != null && state != null) {
            return userRepository.findByUsernameContainingAndState(username, state, pageable).getContent();
        } else if (email != null && state != null) {
            return userRepository.findByEmailContainingAndState(email, state, pageable).getContent();
        } else if (username != null) {
            return userRepository.findByUsernameContaining(username, pageable).getContent();
        } else if (email != null) {
            return userRepository.findByEmailContaining(email, pageable).getContent();
        } else if (state != null) {
            return userRepository.findByState(state, pageable).getContent();
        } else {
            return getUsers(page, size);
        }
    }
}
