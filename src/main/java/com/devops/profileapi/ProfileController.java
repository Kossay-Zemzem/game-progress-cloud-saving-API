package com.devops.profileapi;

import com.devops.profileapi.Exceptions.ProfileNotFoundException;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@Slf4j
@RestController
public class ProfileController {
    ProfileRepository profileRepository;
    //Metrics
    private final Counter profileCreateCounter;
    private final Counter profileFetchCounter;
    private final Timer profileFetchTimer;


    ProfileController(ProfileRepository profileRepository, MeterRegistry meterRegistry) {
        this.profileRepository = profileRepository;

        this.profileCreateCounter = Counter.builder("profiles.create.request")
                .description("Total number of profiles creation requests")
                .register(meterRegistry);
        this.profileFetchCounter = Counter.builder("profiles.fetch.total")
                .description("Total number of profile fetch requests")
                .register(meterRegistry);
        this.profileFetchTimer = Timer.builder("profiles.fetch.timer")
                .description("Time taken to fetch profiles")
                .register(meterRegistry);
    }

    @GetMapping("/profiles")
    public List<Profile> getAllProfiles() {
        log.info("method=GET path=/profiles desc=Fetching all profiles");
        return profileFetchTimer.record(() -> {
                profileRepository.findAll();
                profileFetchCounter.increment(); //metric increment
                return profileRepository.findAll();
        });
    }

    @GetMapping("/profiles/{id}")
    public Profile getProfileById(@PathVariable("id") Long id) {
        log.info("method=GET path=/profiles/{} desc=Fetching profile by id", id);
        profileFetchCounter.increment(); //metric increment
        return profileRepository.findById(id).orElseThrow(() -> new ProfileNotFoundException(id));
    }

    @PostMapping("/profiles")
    public ResponseEntity<Profile> createProfile(@Valid @RequestBody Profile profile) {
        log.info("method=POST path=/profiles desc=Creating new profile");
        Profile savedProfile = profileRepository.save(profile);
        profileCreateCounter.increment(); //metric increment
        URI location = URI.create(String.format("/profiles/%s", savedProfile.getId())); //return the location of the created resource as per REST conventions
        return ResponseEntity.created(location).body(savedProfile); //returns 201 created as per change request
    }

    @DeleteMapping("/profiles/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable("id") Long id) {
        log.info("method=DELETE path=/profiles/{} desc=Deleting profile by id", id);
        // Verify profile exists before deleting
        profileRepository.findById(id).orElseThrow(() -> new ProfileNotFoundException(id));
        profileRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
