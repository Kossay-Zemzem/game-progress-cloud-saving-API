package com.devops.profileapi.Exceptions;

public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(Long id) {
        super(String.format("Profile with id %d not found", id));
    }
}
