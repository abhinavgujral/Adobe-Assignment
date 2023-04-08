package com.SocialMedia.Backend.Service;

import com.SocialMedia.Backend.Repository.PostRepository;
import jakarta.persistence.SecondaryTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;
}
