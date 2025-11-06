package com.job.demo.model;



import jakarta.persistence.*;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name; // e.g., "Node.js", "React", "Docker"

    // This now points to the "skills" field in UserProfile
    @ManyToMany(mappedBy = "skills")
    @JsonIgnore
    private Set<UserProfile> userProfiles;

    // --- Constructors ---
    public Skill() { }

    public Skill(String name) {
        this.name = name;
    }

    // --- Getters and Setters ---
    // (Generate for all fields)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }
    // ...
}