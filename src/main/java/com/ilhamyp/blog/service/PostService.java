package com.ilhamyp.blog.service;

import com.ilhamyp.blog.entity.Post;
import com.ilhamyp.blog.exception.ApiException;
import com.ilhamyp.blog.mapper.PostMapper;
import com.ilhamyp.blog.repository.PostRepository;
import com.ilhamyp.blog.request.post.CreatePostRequest;
import com.ilhamyp.blog.request.post.GetPostBySlugRequest;
import com.ilhamyp.blog.request.post.GetPostsRequest;
import com.ilhamyp.blog.request.post.UpdatePostBySlugRequest;
import com.ilhamyp.blog.response.post.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PostService {

    @Autowired
    PostRepository postRepository;

    public GetPostResponse getPostBySlug(GetPostBySlugRequest request){
        Post post = postRepository.findBySlugAndIsDeleted(request.getSlug(), false)
                .orElseThrow(() -> new ApiException("Not Found", HttpStatus.NOT_FOUND));
        return PostMapper.INSTANCE.mapToGetPostResponse(post);
    }

    public CreatePostResponse createPost(CreatePostRequest request){
        Post post = PostMapper.INSTANCE.mapFromCreatePostRequest(request);
        post.setCommentCount(0L);
        post.setCreatedAt(Instant.now().getEpochSecond());
        post = postRepository.save(post);

        return PostMapper.INSTANCE.mapToCreatePostResponse(post);
    }

    public UpdatePostBySlugResponse editPostBySlug(String slug, UpdatePostBySlugRequest request){
        Post post = postRepository.findBySlugAndIsDeleted(slug, false)
                .orElseThrow(() -> new ApiException("Not Found", HttpStatus.NOT_FOUND));

        post.setTitle(request.getTitle());
        post.setBody(request.getBody());
        post.setSlug(request.getSlug());
        postRepository.save(post);

        return PostMapper.INSTANCE.mapToUpdatePostBySlugResponse(post);
    }

    public DeletePostByIdResponse deletePostById(Integer id){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ApiException("post not found", HttpStatus.NOT_FOUND));
        post.setDeleted(true);
        postRepository.save(post);
        return DeletePostByIdResponse.builder().id(id).slug(post.getSlug()).build();
    }

    public PublishPostResponse publishPost(Integer id){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ApiException("post not found", HttpStatus.NOT_FOUND));
        post.setPublished(true);
        post.setPublishedAt(Instant.now().getEpochSecond());
        postRepository.save(post);
        return PublishPostResponse.builder().publishedAt(post.getPublishedAt()).build();
    }

    public List<GetPostResponse> getPosts(GetPostsRequest request) {
        List<Post> posts = postRepository.findByIsDeleted(false);
        List<GetPostResponse> responses = new ArrayList<>();
        posts.forEach(post -> responses.add(PostMapper.INSTANCE.mapToGetPostResponse(post)));
        return responses;
    }
}
