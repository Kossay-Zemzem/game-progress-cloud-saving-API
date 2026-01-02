package com.devops.profileapi;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@Valid
@Slf4j
@RestController
public class ProfileController {
    ProfileRepository profileRepository;
    ProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @GetMapping("/profiles")
    public List<Profile> getAllProfiles() {
        log.info("method=GET path=/profiles desc=Fetching all profiles");
        return profileRepository.findAll();
    }

    @GetMapping("/profiles/{id}")
    public Profile getProfileById(@PathVariable("id") Long id) {
        log.info("method=GET path=/profiles/{} desc=Fetching profile by id", id);
        return profileRepository.findById(id).orElse(null);
    }

    @PostMapping("/profiles")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        log.info("method=POST path=/profiles desc=Creating new profile, id={}", profile.getId());
        Profile savedProfile = profileRepository.save(profile);
        URI location = URI.create(String.format("/profiles/%s", savedProfile.getId())); //return the location of the created resource as per REST conventions
        return ResponseEntity.created(location).body(savedProfile); //returns 201 created as per change request
    }
}
