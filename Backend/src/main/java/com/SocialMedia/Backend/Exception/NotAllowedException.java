package com.SocialMedia.Backend.Exception;

public class NotAllowedException extends RuntimeException {
    public NotAllowedException(String NotAllowed) {
        super(NotAllowed);
    }
}
