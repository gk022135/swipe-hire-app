package com.job.demo.model;



import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore; // Import this

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- The Link back to the User ---
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // This creates the user_id foreign key
    @JsonIgnore // Prevents infinite loops when sending JSON
    private User user;

    private String profilePictureUrl; // URL to the stored image
    @Column(length = 1000) // Give the bio field more length
    private String bio;
    // --- Step 1 Fields ---
    private String targetRole;
    private int experienceYears;
    private String resumeUrl;
    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getResumeUrl() { return resumeUrl; }
    public void setResumeUrl(String resumeUrl) { this.resumeUrl = resumeUrl; }

    public boolean isRemoteOnly() {
        return remoteOnly;
    }

    public void setRemoteOnly(boolean remoteOnly) {
        this.remoteOnly = remoteOnly;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    public String getGithubProfile() {
        return githubProfile;
    }

    public void setGithubProfile(String githubProfile) {
        this.githubProfile = githubProfile;
    }

    public String getLinkedinProfile() {
        return linkedinProfile;
    }

    public void setLinkedinProfile(String linkedinProfile) {
        this.linkedinProfile = linkedinProfile;
    }

    // --- Step 3 Fields ---
    private boolean remoteOnly;
    private String preferredLocation;
    private int minSalary;

    // --- Step 4 Fields ---
    private String githubProfile;
    private String linkedinProfile;

    // --- Step 2 (Skills) - This table owns the relationship ---
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "profile_skills",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills = new HashSet<>();

    // --- Getters and Setters ---
    // (Right-click -> Generate -> Getters and Setters for ALL fields)
    // ...
    // Example:
    public String getProfilePictureUrl() { return profilePictureUrl; }
    public void setProfilePictureUrl(String profilePictureUrl) { this.profilePictureUrl = profilePictureUrl; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getTargetRole() { return targetRole; }
    public void setTargetRole(String targetRole) { this.targetRole = targetRole; }
    public Set<Skill> getSkills() { return skills; }
    public void setSkills(Set<Skill> skills) { this.skills = skills; }
    // ... generate for all other fields ...
}