package com.job.demo.controller;

import com.job.demo.dto.ProfileSetupRequest;
import com.job.demo.model.Skill;
import com.job.demo.model.User;
import com.job.demo.model.UserProfile; // Import new model
import com.job.demo.repository.SkillRepository;
import com.job.demo.repository.UserRepository;
import com.job.demo.repository.UserProfileRepository; // Import new repo
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors; // <-- ADD THIS IMPORT

// --- ADD THESE IMPORTS ---
import org.springframework.web.bind.annotation.GetMapping;
import com.job.demo.dto.ProfileResponse;
// --- END NEW IMPORTS ---


@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository; // Add new repo

    @Autowired
    private SkillRepository skillRepository;

    // This is your existing method, unchanged
    @PostMapping("/setup")
    public ResponseEntity<?> setupProfile(Authentication authentication, @RequestBody ProfileSetupRequest request) {

        // 1. Get the logged-in user
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // 2. Get or create profile
        UserProfile profile = user.getUserProfile();
        if (profile == null) {
            profile = new UserProfile();
            profile.setUser(user); // Link profile to the user
        }

        // 3. Map all data
        profile.setBio(request.getBio());
        profile.setTargetRole(request.getTargetRole());
        profile.setExperienceYears(request.getExperienceYears());
        profile.setRemoteOnly(request.isRemoteOnly());
        profile.setPreferredLocation(request.getPreferredLocation());
        profile.setMinSalary(request.getMinSalary());
        profile.setGithubProfile(request.getGithubProfile());
        profile.setLinkedinProfile(request.getLinkedinProfile());

        // 4. Handle Skills
        Set<Skill> skillSet = new HashSet<>();
        // Add null check for skills
        if (request.getSkills() != null) {
            for (String skillName : request.getSkills()) {
                Skill skill = skillRepository.findByName(skillName)
                        .orElseGet(() -> new Skill(skillName));

                skillRepository.save(skill);
                skillSet.add(skill);
            }
        }
        profile.setSkills(skillSet);

        // 5. Save the new or updated UserProfile.
        userProfileRepository.save(profile);

        return ResponseEntity.ok("Profile updated successfully!");
    }


    // This is your existing method, unchanged
    @PostMapping("/upload-photo")
    public ResponseEntity<?> uploadProfilePicture(
            Authentication authentication,
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Error: No file uploaded.");
        }

        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserProfile profile = user.getUserProfile();
        if (profile == null) {
            profile = new UserProfile();
            profile.setUser(user);
        }

        try {
            String filename = user.getId() + "_" + file.getOriginalFilename();
            String imageUrl = "/uploads/profile-pictures/" + filename;

            profile.setProfilePictureUrl(imageUrl);
            userProfileRepository.save(profile);

            return ResponseEntity.ok(imageUrl);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }

    // This is your existing method, unchanged
    @PostMapping("/upload-resume")
    public ResponseEntity<?> uploadResume(
            Authentication authentication,
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Error: No file uploaded.");
        }

        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserProfile profile = user.getUserProfile();
        if (profile == null) {
            profile = new UserProfile();
            profile.setUser(user);
        }

        try {
            String filename = "user_" + user.getId() + "_resume_" + file.getOriginalFilename();
            String resumeUrl = "/uploads/resumes/" + filename;

            profile.setResumeUrl(resumeUrl);
            userProfileRepository.save(profile);

            return ResponseEntity.ok(resumeUrl);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }


    // --- THIS IS THE NEWLY ADDED METHOD ---

    /**
     * Fetches the complete profile for the currently authenticated user.
     */
    @GetMapping("/me")
    public ResponseEntity<ProfileResponse> getMyProfile(Authentication authentication) {

        // 1. Get the logged-in user
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        // 2. Find the associated profile
        UserProfile profile = user.getUserProfile();

        // 3. Create the DTO
        ProfileResponse response = new ProfileResponse();

        // 4. Populate from User object
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());

        // 5. Populate from UserProfile (if it exists)
        if (profile != null) {
            response.setTargetRole(profile.getTargetRole());
            response.setExperienceYears(profile.getExperienceYears());
            response.setBio(profile.getBio());
            response.setRemoteOnly(profile.isRemoteOnly());
            response.setPreferredLocation(profile.getPreferredLocation());
            response.setMinSalary(profile.getMinSalary());
            response.setGithubProfile(profile.getGithubProfile());
            response.setLinkedinProfile(profile.getLinkedinProfile());
            response.setProfilePictureUrl(profile.getProfilePictureUrl());
            response.setResumeUrl(profile.getResumeUrl());

            // Convert Set<Skill> to Set<String>
            if (profile.getSkills() != null) {
                response.setSkills(
                        profile.getSkills().stream()
                                .map(Skill::getName)
                                .collect(Collectors.toSet())
                );
            }
        }

        return ResponseEntity.ok(response);
    }
}