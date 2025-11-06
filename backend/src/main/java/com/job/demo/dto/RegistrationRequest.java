package com.job.demo.dto;



// This is just a plain Java object (POJO) to hold the request data
public class RegistrationRequest {
    private String fullName;
    private String email;
    private String password;
    private String confirmPassword;
    private String phoneNumber;
    // --- Generate Getters and Setters for all fields ---
    // (Right-click -> Generate -> Getter and Setter)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
}