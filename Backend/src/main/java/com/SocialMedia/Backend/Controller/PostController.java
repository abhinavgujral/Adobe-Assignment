package com.SocialMedia.Backend.Controller;

import com.SocialMedia.Backend.Entity.Post;
import com.SocialMedia.Backend.Entity.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PostController {

    /*
     Request type: POST
     URI: /v1/Users
     What is does? : Create or Persist a new user.
     Body of request : need to send the User object.
   */
    @PostMapping("/v1/posts")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user){

        return new ResponseEntity<>("user created Successfully", HttpStatus.CREATED);
    }

    /*
      Request type: GET
      URI: /v1/users/{id}
      What is does?  Retrieve a post by id.
      Body of request : need to send the Post id as path variable.
    */


    @GetMapping("/v1/posts/{id}")
    public ResponseEntity<Post> getUserById(@PathVariable("id") Integer id){

        return new ResponseEntity<>(new Post(),HttpStatus.ACCEPTED );
    }
    /*
          Request type: PATCH
          URI: /v1/posts/{id}
          What is does?  update post content by id.
          Body of request : need to send the post id as path variable and Send the content field you want to update as a body
        */
    @PatchMapping("/v1/posts/{id}")
    public ResponseEntity<String> updateUser( @PathVariable Long id, @RequestBody Map<String, Object> fields){

        return new ResponseEntity<>("Updated Successfully",HttpStatus.OK);

    }

    /*
      Request type: DELETE
      URI: /v1/posts/{id}:
      What is does ? Delete a post by id.
      Body of request : need to send the post id as path variable.
    */

    @DeleteMapping("/v1/posts/{id}")
    public ResponseEntity<String> deleteUser( @PathVariable Long id)
    {

        return new ResponseEntity<>("Updated Successfully",HttpStatus.OK);

    }

         /*
      Request type: POST
      URI: /v1/posts/{id}/like
      What is does ? Increment the like count of a post by id.
      Body of request : need to send the post id as path variable.
    */

    @PostMapping("/v1/posts/{id}/like")
    public ResponseEntity<String> incrementLikeOnPost(@PathVariable("id") Integer id){
        return new ResponseEntity<>("Liked",HttpStatus.ACCEPTED);
    }



       /*
      Request type: POST
      URI: /v1/posts/{id}/unlike
      What is does ? Decrement the like count of a post by id.
      Body of request : need to send the post id as path variable.
    */
       @PostMapping("/v1/posts/{id}/unlike")
       public ResponseEntity<String> decrementLikeOnPost(@PathVariable("id") Integer id){
           //The count
           //    should not go below 0.
           return new ResponseEntity<>("Liked",HttpStatus.ACCEPTED);
       }

      /*
      Request type: GET
      URI: /v1/analytics/posts
      What is does ? Retrieve the total number of posts.
      Body of request : NONE
    */
    @GetMapping("/v1/analytics/posts")
    public ResponseEntity<List<Post>> getPostAnalytics()
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }
/*
      Request type: GET
      URI: /v1/analytics/posts/top-liked
      What is does ? Retrieve the top 5 most liked posts.
      Body of request : NONE
    */

    @GetMapping("/v1/analytics/posts/top-liked")
    public ResponseEntity<List<Post>> getTopPosts(){

        return new ResponseEntity<>(HttpStatus.OK);
    }


    }
