package com.backend.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String clubId;
    private String state;
    private String profilePicture;
    private String role = "user";

 

    public User(String id, String firstName, String lastName, String email, String password, String clubId,
            String state, String profilePicture, String role, String description) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.clubId = clubId;
        this.state = state;
        this.profilePicture = profilePicture;
        this.role = role;
        this.description = description;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", password=" + password + ", clubId=" + clubId + ", state=" + state + ", profilePicture="
                + profilePicture + ", role=" + role + ", description=" + description + "]";
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }


    private String description;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getClubId() {
        return clubId;
    }
    public void setClubId(String clubId) {
        this.clubId = clubId;
    }
    public String getProfilePicture() {
        return profilePicture;
    }
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }


    public boolean equals(String password) {
        return this.password.equals(password);
    }
}
