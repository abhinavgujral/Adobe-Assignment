package com.SocialMedia.Backend.Exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String notAbleToSaveUser) {
        super(notAbleToSaveUser);
    }
}
