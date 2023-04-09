package com.SocialMedia.Backend.Service;

import com.SocialMedia.Backend.Entity.Post;
import com.SocialMedia.Backend.Entity.User;
import com.SocialMedia.Backend.Exception.NotAllowedException;
import com.SocialMedia.Backend.Exception.ResourceNotFoundException;
import com.SocialMedia.Backend.Repository.PostRepository;
import com.SocialMedia.Backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                    if(! (value instanceof String))
                        throw new IllegalArgumentException("User id must be an String");
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
     //  post.setLikestatus(false);

        return postRepository.save(post);
    }

    public Post getPostById(Integer id) {
        Post fetchPost= postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post not found"));
          return fetchPost;
    }

    public Post updatePost(Integer postId, Map<String, Object> fields) {
        Post fetchedPost= postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post not found"));
        if(!fetchedPost.getUser().equals(userRepository.findById((Integer) fields.get("userId")).orElseThrow(()->new ResourceNotFoundException("user with id: "+postId+" is not present")))){
            throw new NotAllowedException("user with id: "+fields.get("userId")+" hasn't posted the post with id:"+postId);
        }
       fields.forEach((key,value)->{
           switch(key){
               case "content":
                   fetchedPost.setContent((String) value);
           }
       });
        return postRepository.save(fetchedPost);

    }

    public String deletePostById(Integer id) {
        Post fetchedPost= postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post is already deleted or not found"));
        postRepository.deleteById(id);
        return "Post Deleted ";
    }


    public Post incrementLikedPost(Integer id) {
        System.out.println("hello1");
        Post fetchedPost= postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post not found"));
        System.out.println("hello2");
        //System.out.println(fetchedPost.toString());
        fetchedPost.setLikes(fetchedPost.getLikes()+1);
        System.out.println("hello3");
        return   postRepository.save(fetchedPost);

    }

    public Post decrementLikedPost(Integer id) {
        Post fetchedPost= postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post not found"));
        fetchedPost.setLikes(fetchedPost.getLikes()>0?fetchedPost.getLikes()-1:0);;
        return   postRepository.save(fetchedPost);
    }

    public Integer getAllPost() {
        List<Post> allPostList= postRepository.findAll();
        return  allPostList.size();
    }

    public List<Post> getTopPost() {

        List<Post> sortedPostList=postRepository.findAll().stream().sorted((o1, o2)->o2.getLikes().compareTo(o1.getLikes())).collect(Collectors.toList());
        List<Post> topFivePost = new ArrayList<>(5);
        if(sortedPostList.size()<5)
            throw new ResourceNotFoundException("There are less than 5 users");
        for(int i=0;i<5;i++)
            topFivePost.add(sortedPostList.get(i));
        return topFivePost;

    }
}
