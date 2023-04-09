package com.SocialMedia.Backend.Service;

import com.SocialMedia.Backend.Entity.User;
import com.SocialMedia.Backend.Exception.ResourceAlreadyExistException;
import com.SocialMedia.Backend.Exception.ResourceNotFoundException;
import com.SocialMedia.Backend.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public User createUser(@Valid Map<String, Object> fields) {

        User user = new User();
        fields.forEach((key, value) -> {
            System.out.println(key);
            switch (key) {
                case "name":
                    user.setName((String) value);
                    break;

                case "email":
                    if (!userRepository.findByEmail((String) value).isEmpty())
                        throw new ResourceAlreadyExistException("user with this email is Already present");
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


    public User updateUser(Integer id, Map<String, Object> fields) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        fields.forEach((key, value) -> {
            switch (key) {
                case "name":
                    existingUser.setName((String) value);
                    break;
                case "email":
                    existingUser.setEmail((String) value);
                    break;
                case "bio":
                    existingUser.setBio((String) value);
                    break;
            }
        });
        existingUser.setUpdatedAt(LocalDateTime.now());

      return  userRepository.save(existingUser);
    }


    public String deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
        }
        catch (Exception e){
            throw new ResourceNotFoundException("user with id is not present");
        }
        return "User deleted successfully";
    }

    public Integer getCountUser() {
        List<User> userList=userRepository.findAll();
        return userList.size();
    }

    public List<User> getTopActiveUser() {
        List<User> userList=userRepository.findAll();
        userList.sort((o1,o2)-> o1.getPosts().size() - o2.getPosts().size());
        List<User> topFive= new ArrayList<>(5);
        for(int i =0;i<5;i++)
            topFive.add(userList.get(i));
        return topFive;
    }
}
