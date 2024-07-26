package com.backend.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clubs")
public class Club {
    @Id
    private String id;
    private String email;
    private String password;
    private String clubName;
    private boolean state;
    private String universityName;
    private String specialty;
    private String logo;
    private String universityCertificate;
    private String role = "club";

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUniversityCertificate() {
        return universityCertificate;
    }

    public void setUniversityCertificate(String universityCertificate) {
        this.universityCertificate = universityCertificate;
    }

    public Club(String id, String email, String password, String clubName, boolean state, String universityName,
            String specialty, String logo, String universityCertificate, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.clubName = clubName;
        this.state = state;
        this.universityName = universityName;
        this.specialty = specialty;
        this.logo = logo;
        this.universityCertificate = universityCertificate;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Club [id=" + id + ", email=" + email + ", password=" + password + ", clubName=" + clubName + ", state="
                + state + ", universityName=" + universityName + ", specialty=" + specialty + ", logo=" + logo
                + ", universityCertificate=" + universityCertificate + ", role=" + role + "]";
    }



}
