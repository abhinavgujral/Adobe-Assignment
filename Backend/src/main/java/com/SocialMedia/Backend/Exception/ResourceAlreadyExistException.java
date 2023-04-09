package com.SocialMedia.Backend.Exception;

public class ResourceAlreadyExistException extends RuntimeException {
    public ResourceAlreadyExistException(String duplicateNotAllowed) {
        super(duplicateNotAllowed);
    }
}
