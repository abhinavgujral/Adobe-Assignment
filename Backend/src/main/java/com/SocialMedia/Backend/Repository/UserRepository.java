package com.SocialMedia.Backend.Repository;

import com.SocialMedia.Backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Integer> {
}
