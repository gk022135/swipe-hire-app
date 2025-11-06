package com.job.demo.dto;


import java.util.List;


public class ProfileSetupRequest {
    // Step 1
    private String bio;
    private String targetRole;
    private int experienceYears;
    // Step 2
    private List<String> skills; // ["Node.js", "MongoDB"]
    // Step 3
    private boolean remoteOnly;

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public String getTargetRole() {
        return targetRole;
    }

    public void setTargetRole(String targetRole) {
        this.targetRole = targetRole;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public boolean isRemoteOnly() {
        return remoteOnly;
    }

    public void setRemoteOnly(boolean remoteOnly) {
        this.remoteOnly = remoteOnly;
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

    private String preferredLocation;
    private int minSalary;
    // Step 4
    private String githubProfile;
    private String linkedinProfile;

    // --- Generate Getters and Setters for ALL fields ---
    // ...
}
