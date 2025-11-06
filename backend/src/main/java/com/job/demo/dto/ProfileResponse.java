package com.job.demo.dto;



import java.util.Set;

// This DTO will combine data from User and UserProfile
public class ProfileResponse {

    // From User
    private String fullName;
    private String email;
    private String phoneNumber;

    // From UserProfile
    private String targetRole;
    private int experienceYears;
    private String bio;
    private Set<String> skills;
    private boolean remoteOnly;
    private String preferredLocation;
    private int minSalary;
    private String githubProfile;
    private String linkedinProfile;
    private String profilePictureUrl;
    private String resumeUrl;

    // --- Generate Getters and Setters for ALL fields ---
    // (Right-click -> Generate -> Getter and Setter -> Select All)

    // Example getters/setters:
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getTargetRole() { return targetRole; }
    public void setTargetRole(String targetRole) { this.targetRole = targetRole; }
    public int getExperienceYears() { return experienceYears; }
    public void setExperienceYears(int experienceYears) { this.experienceYears = experienceYears; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public Set<String> getSkills() { return skills; }
    public void setSkills(Set<String> skills) { this.skills = skills; }
    public boolean isRemoteOnly() { return remoteOnly; }
    public void setRemoteOnly(boolean remoteOnly) { this.remoteOnly = remoteOnly; }
    public String getPreferredLocation() { return preferredLocation; }
    public void setPreferredLocation(String preferredLocation) { this.preferredLocation = preferredLocation; }
    public int getMinSalary() { return minSalary; }
    public void setMinSalary(int minSalary) { this.minSalary = minSalary; }
    public String getGithubProfile() { return githubProfile; }
    public void setGithubProfile(String githubProfile) { this.githubProfile = githubProfile; }
    public String getLinkedinProfile() { return linkedinProfile; }
    public void setLinkedinProfile(String linkedinProfile) { this.linkedinProfile = linkedinProfile; }
    public String getProfilePictureUrl() { return profilePictureUrl; }
    public void setProfilePictureUrl(String profilePictureUrl) { this.profilePictureUrl = profilePictureUrl; }
    public String getResumeUrl() { return resumeUrl; }
    public void setResumeUrl(String resumeUrl) { this.resumeUrl = resumeUrl; }
}