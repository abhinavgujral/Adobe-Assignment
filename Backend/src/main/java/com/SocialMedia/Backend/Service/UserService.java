package com.SocialMedia.Backend.Service;

import com.SocialMedia.Backend.Entity.User;
import com.SocialMedia.Backend.Exception.ResourceNotFoundException;
import com.SocialMedia.Backend.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public User createUser(@Valid Map<String, Object> fields) {
        //User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        User user = new User();
        fields.forEach((key, value) -> {
            switch (key) {
                case "name":
                    user.setName((String) value);
                    break;
                case "email":
                    user.setEmail((String) value);
                    break;
                case "bio":
                    user.setBio((String) value);
                    break;
            }
        });

           if(!fields.containsKey("bio"))
               user.setBio("");

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

       return userRepository.save(user);
    }

    public User getUserById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser.orElseThrow(() -> new ResourceNotFoundException("User with Id: "+id+"is not present in database"));
        return optionalUser.get();
    }


}
