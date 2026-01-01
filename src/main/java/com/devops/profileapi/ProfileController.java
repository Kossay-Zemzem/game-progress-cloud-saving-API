package com.devops.profileapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
public class ProfileController {
    ProfileRepository profileRepository;
    ProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @GetMapping("/profiles")
    public List<Profile> getAllProfiles() {
        log.info("method=POST path=/profiles desc=Fetching all profiles");
        return profileRepository.findAll();
    }

    @GetMapping("/profiles/{id}")
    public Profile getProfileById(@PathVariable("id") Long id) {
        log.info("method=POST path=/profiles/{} desc=Fetching profile by id", id);
        return profileRepository.findById(id).orElse(null);
    }
}
