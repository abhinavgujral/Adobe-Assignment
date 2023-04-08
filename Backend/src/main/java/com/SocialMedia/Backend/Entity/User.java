package com.SocialMedia.Backend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class User {

    @Id
    private Integer id;// (unique identifier)
    @Pattern(regexp = "^[A-Z][a-z]*", message = " the first letter should be uppercase and the rest of the letters can be lowercase.")
    @Size(min = 1, max = 50, message = "username length should be between 1 and 50")
    private String name;// (string, 1-50 characters)
    @Email(message = "Email should be of format example@xyz.com")
    private String email;// (string, valid email format)
    @Size(min = 0, max = 200, message = "Bio length should be between 0 and 200")
    private String bio;// (optional string, 0-200 characters)
    private LocalDateTime created_at;// (timestamp, automatically set when the user is created)
    private LocalDateTime updated_at;// (timestamp, automatically updated when the user is updated)

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
        updated_at = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = LocalDateTime.now();
    }
}
