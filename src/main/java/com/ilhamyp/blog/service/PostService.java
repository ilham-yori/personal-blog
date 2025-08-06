package com.ilhamyp.blog.service;

import com.ilhamyp.blog.entity.Post;
import com.ilhamyp.blog.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Post getPostBySlug(String slug){
        return postRepository.findBySlug(slug).orElse(null);
    }

    public Post createPost(Post post){
        post.setCreatedAt(Instant.now().getEpochSecond());
        return postRepository.save(post);
    }

    public Post editPostBySlug(String slug, Post updatedPost){
        Post savedPost = postRepository.findBySlug(slug).orElse(null);

        if (savedPost == null){
            return null;
        }

        updatedPost.setId(savedPost.getId());
        updatedPost.setCreatedAt(Instant.now().getEpochSecond());

        return postRepository.save(updatedPost);
    }

    public Boolean deletePostById(Integer id){
        Post post = postRepository.findById(id).orElse(null);
        if (post == null){
            return false;
        }
        postRepository.deleteById(id);
        return true;
    }

    public Post publishPost(Integer id){
        Post post = postRepository.findById(id).orElse(null);
        if (post == null){
            return null;
        }
        post.setPublished(true);
        post.setPublishedAt(Instant.now().getEpochSecond());
        return post;
    }

    public Iterable<Post> getPosts() {
        return postRepository.findAll();
    }
}
