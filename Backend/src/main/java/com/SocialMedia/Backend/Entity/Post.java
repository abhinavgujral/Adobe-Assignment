package com.SocialMedia.Backend.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // (unique identifier for post)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //(foreign key referencing the User model)
    @Size(min = 1, max = 300, message = "Content length should be between 1 and 300")
    public String content;  // (string, 1-300 characters)
    private LocalDateTime created_at; // (timestamp, automatically set when the post is created)
    private LocalDateTime updated_at; // (timestamp, automatically updated when the post is updated)
    @Min(0)
    @NotNull
    private Integer likes; // (integer, non-negative)

;
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
