package com.SocialMedia.Backend.Controller;

import com.SocialMedia.Backend.Entity.User;
import com.SocialMedia.Backend.Repository.UserRepository;
import com.SocialMedia.Backend.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    /*
       Request type: POST
       URI: /v1/Users
       What is does? : Create or Persist a new user.
       Body of request : need to send the User object.
     */
    @PostMapping("/v1/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody Map<String, Object> fields) {

        User savedUser = userService.createUser(fields);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    /*
      Request type: GET
      URI: /v1/users/{id}
      What is does?  Retrieve a user by id.
      Body of request : need to send the User id as path variable.
    */


    @GetMapping("/v1/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        User fetchedUser = userService.getUserById(id);
        return new ResponseEntity<>(fetchedUser, HttpStatus.ACCEPTED);
    }

    /*
      Request type: PATCH
      URI: /v1/users/{id}
      What is does?  update username or bio by id.
      Body of request : need to send the User id as path variable and Send fields you want to update
    */
    @PatchMapping("/v1/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody Map<String, Object> fields) {
        User updatedUser = userService.updateUser(id, fields);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);

    }

    /*
      Request type: DELETE
      URI: /v1/users/{id}:
      What is does ? Delete a user by id.
      Body of request : need to send the User id as path variable.
    */

    @DeleteMapping("/v1/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        String status = userService.deleteUser(id);
        return new ResponseEntity<>(status, HttpStatus.OK);

    }

    /*
     Request type: GET
     URI: /v1/analytics/users
     What is does ? Retrieve the total number of users.
     Body of request : NONE.
   */
    @GetMapping("/v1/analytics/users")
    public ResponseEntity<Integer> getUserTotal() {
          Integer totalUser= userService.getCountUser();
        return new ResponseEntity<>(totalUser, HttpStatus.OK);
    }

    /*
     Request type: GET
     URI: /v1/analytics/users/top-active
     What is does ? Retrieve the top 5 most active users, based on the number of posts.
     Body of request : NONE.
   */
    @GetMapping("/v1/analytics/users/top-active")
    public ResponseEntity<List<User>> topFiveUser() {
        List<User> topActiveUser=userService.getTopActiveUser();
        return new ResponseEntity<>(topActiveUser, HttpStatus.OK);
    }


}
