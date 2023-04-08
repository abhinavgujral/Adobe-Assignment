package com.SocialMedia.Backend.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;// (unique identifier)
    @Pattern(regexp = "^[A-Z][a-z]*", message = " the first letter should be uppercase and the rest of the letters can be lowercase.")
    @Size(min = 1, max = 50, message = "username length should be between 1 and 50")
    @NotNull(message = "User name should not be null")
    private String name;// (string, 1-50 characters)
    @Email(message = "Email should be of format example@xyz.com")
    @Column(unique = true)
    @NotNull(message = "User email should not be null")
    private  String email;// (string, valid email format)
    @Size(min = 0, max = 200, message = "Bio length should be between 0 and 200")
    private String bio;// (optional string, 0-200 characters)
    private LocalDateTime createdAt;// (timestamp, automatically set when the user is created)
    private LocalDateTime updatedAt;// (timestamp, automatically updated when the user is updated)

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
