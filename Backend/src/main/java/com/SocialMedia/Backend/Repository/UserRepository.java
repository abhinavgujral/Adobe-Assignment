package com.SocialMedia.Backend.Repository;

import com.SocialMedia.Backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Integer> {
    @Query("UPDATE User u SET u.name = :name, u.email = :email, u.bio= :bio WHERE u.id = :id")
    User update(@Param("id") Integer id, @Param("name") String name, @Param("email") String email, @Param("bio") String bio);

    Optional<User> findByEmail(String value);
}
