package com.SocialMedia.Backend.Service;

import com.SocialMedia.Backend.Entity.Post;
import com.SocialMedia.Backend.Entity.User;
import com.SocialMedia.Backend.Exception.ResourceNotFoundException;
import com.SocialMedia.Backend.Repository.PostRepository;
import com.SocialMedia.Backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    public Post createPost(Map<String, Object> fields) {
        Post post = new Post();
        fields.forEach((key, value) -> {
            switch (key) {
                case "content":
                  post.setContent((String) value);
                    break;

                case "userId":
                    if(! (value instanceof Integer))
                        throw new IllegalArgumentException("User id must be an Integer");


                    User fetchedUser=userRepository.findById((Integer)value).orElseThrow(()-> new ResourceNotFoundException("user with this id is absent"));
                    post.setUser(fetchedUser);
                    break;

            }
        });

        post.setLikes(0);

        return postRepository.save(post);
    }

    public Post getPostById(Integer id) {
        Post fetchPost= postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post not found"));
          return fetchPost;
    }

    public Post updatePost(Integer id, Map<String, Object> fields) {
        Post fetchedPost= postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post not found"));
       fields.forEach((key,value)->{
           switch(key){
               case "content":
                   fetchedPost.setContent((String) value);
           }
       });
        return postRepository.save(fetchedPost);

    }

    public String deletePostById(Integer id) {
        postRepository.deleteById(id);
        return "post Deleted ";
    }


}
