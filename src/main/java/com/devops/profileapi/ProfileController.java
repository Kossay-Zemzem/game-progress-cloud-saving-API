package com.devops.profileapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfileController {
    ProfileRepository profileRepository;
    ProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
    @GetMapping("/profiles")
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }
}
