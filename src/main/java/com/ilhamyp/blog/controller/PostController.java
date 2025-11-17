package com.ilhamyp.blog.controller;

import com.ilhamyp.blog.entity.Post;
import com.ilhamyp.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public Iterable<Post> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/{slug}")
    public Post getPostBySlug(@PathVariable String slug){
        return postService.getPostBySlug(slug);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post){
        return postService.createPost(post);
    }

    @PutMapping("/{slug}")
    public Post editPostBySlug(@PathVariable String slug, @RequestBody Post updatedPost){
        return postService.editPostBySlug(slug,updatedPost);
    }

    @DeleteMapping("/{id}")
    public Boolean deletePostById(@PathVariable Integer id){
        return postService.deletePostById(id);
    }

    @PutMapping("/{id}/publish")
    public Post publishPost(@PathVariable Integer id){
        return postService.publishPost(id);
    }

}
