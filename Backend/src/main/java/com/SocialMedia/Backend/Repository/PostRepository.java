package com.SocialMedia.Backend.Repository;

import com.SocialMedia.Backend.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
