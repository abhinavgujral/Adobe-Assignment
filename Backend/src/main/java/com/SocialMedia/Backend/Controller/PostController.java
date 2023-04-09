package com.SocialMedia.Backend.Controller;

import com.SocialMedia.Backend.Entity.Post;

import com.SocialMedia.Backend.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PostController {

    @Autowired
    PostService postService;


    /*
     Request type: POST
     URI: /v1/posts
     What is does? : Create or Persist a new post.
     Body of request : need to send the post fields .
   */
    @PostMapping("/v1/posts")
    public ResponseEntity<String> createPost(@Valid @RequestBody Map<String,Object> fields){
              postService.createPost(fields);
        return new ResponseEntity<>("user created Successfully", HttpStatus.CREATED);
    }

    /*
      Request type: GET
      URI: /v1/posts/{id}
      What is does?  Retrieve a post by id.
      Body of request : need to send the Post id as path variable.
    */


    @GetMapping("/v1/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Integer id){
          Post fetchedPost = postService.getPostById(id);
        return new ResponseEntity<>(fetchedPost,HttpStatus.ACCEPTED );
    }
    /*
          Request type: PATCH
          URI: /v1/posts/{id}
          What is does?  update post content by id.
          Body of request : need to send the post id as path variable and Send the content field you want to update as a body
        */
    @PatchMapping("/v1/posts/{id}")
    public ResponseEntity<Post> updateUser( @PathVariable Integer id, @RequestBody Map<String, Object> fields){
                        Post updatedPost=postService.updatePost(id,fields);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);

    }

    /*
      Request type: DELETE
      URI: /v1/posts/{id}:
      What is does ? Delete a post by id.
      Body of request : need to send the post id as path variable.
    */

    @DeleteMapping("/v1/posts/{id}")
    public ResponseEntity<String> deletePost( @PathVariable Integer id)
    {
        String status = postService.deletePostById(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

         /*
      Request type: POST
      URI: /v1/posts/{id}/like
      What is does ? Increment the like count of a post by id.
      Body of request : need to send the post id as path variable.
    */

    @PatchMapping("/v1/posts/{id}/like")
    public ResponseEntity<Post> incrementLikeOnPost(@PathVariable("id") Integer id){
        Post likedPost= postService.incrementLikedPost(id);
        return new ResponseEntity<>(likedPost,HttpStatus.ACCEPTED);
    }


       /*
      Request type: POST
      URI: /v1/posts/{id}/unlike
      What is does ? Decrement the like count of a post by id.
      Body of request : need to send the post id as path variable.
    */
       @PatchMapping("/v1/posts/{id}/unlike")
       public ResponseEntity<Post> decrementLikeOnPost(@PathVariable("id") Integer id){
           //The count
           //    should not go below 0.
           Post unlikedPost= postService.decrementLikedPost(id);
           return new ResponseEntity<>(unlikedPost,HttpStatus.ACCEPTED);
       }

      /*
      Request type: GET
      URI: /v1/analytics/posts
      What is does ? Retrieve the total number of posts.
      Body of request : NONE
    */
    @GetMapping("/v1/analytics/posts")
    public ResponseEntity<Integer> getPostAnalytics()
    {
        Integer numberOfPost = postService.getAllPost();
        return new ResponseEntity<>(numberOfPost,HttpStatus.OK);
    }
/*
      Request type: GET
      URI: /v1/analytics/posts/top-liked
      What is does ? Retrieve the top 5 most liked posts.
      Body of request : NONE
    */

    @GetMapping("/v1/analytics/posts/top-liked")
    public ResponseEntity<List<Post>> getTopPosts(){
        List<Post> topPostList = postService.getTopPost();
        return new ResponseEntity<>(topPostList,HttpStatus.OK);
    }


    }
