package com.job.demo.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "users") // This will create a table named "users"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true) // No two users can have the same email
    private String email;

    private String password; // This will store the ENCODED password


    private String phoneNumber;

    // ... (Your existing constructors) ...

    // --- Generate Getters and Setters for phoneNumber ---
    // (Right-click -> Generate -> Getter and Setter)
    public User() {
    }


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private UserProfile userProfile;
    // --- ADD THESE TWO METHODS ---
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    // --- Constructors ---


    // --- Getters and Setters ---
    // (You need to generate these)
    // In IntelliJ: Right-click -> Generate -> Getter and Setter -> Select All

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }



    // ... (getters and setters) ...

    // --- ADD GETTER/SETTER for authProvider ---




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
}