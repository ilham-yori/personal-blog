package com.ilhamyp.blog.controller;

import com.ilhamyp.blog.entity.Post;
import com.ilhamyp.blog.request.post.CreatePostRequest;
import com.ilhamyp.blog.request.post.GetPostBySlugRequest;
import com.ilhamyp.blog.request.post.GetPostsRequest;
import com.ilhamyp.blog.request.post.UpdatePostBySlugRequest;
import com.ilhamyp.blog.response.post.*;
import com.ilhamyp.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public List<GetPostResponse> getPosts(@RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                          @RequestParam(required = false, defaultValue = "10") Integer limit){
        GetPostsRequest request = GetPostsRequest.builder()
                .pageNo(pageNo)
                .limit(limit)
                .build();
        return postService.getPosts(request);
    }

    @GetMapping("/{slug}")
    public GetPostResponse getPostBySlug(@Valid @PathVariable String slug){
        GetPostBySlugRequest request = GetPostBySlugRequest.builder().slug(slug).build();
        return postService.getPostBySlug(request);
    }

    @PostMapping
    public CreatePostResponse createPost(@Valid @RequestBody CreatePostRequest createPostRequest){
        return postService.createPost(createPostRequest);
    }

    @PutMapping("/{slug}")
    public UpdatePostBySlugResponse editPostBySlug(@PathVariable String slug,
                                                   @Valid @RequestBody UpdatePostBySlugRequest updatePostBySlugRequest){
        return postService.editPostBySlug(slug, updatePostBySlugRequest);
    }

    @DeleteMapping("/{id}")
    public DeletePostByIdResponse deletePostById(@PathVariable Integer id){
        return postService.deletePostById(id);
    }

    @PutMapping("/{id}/publish")
    public PublishPostResponse publishPost(@Valid @PathVariable Integer id){
        return postService.publishPost(id);
    }

}
