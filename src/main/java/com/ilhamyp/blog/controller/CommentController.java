package com.ilhamyp.blog.controller;

import com.ilhamyp.blog.entity.Comment;
import com.ilhamyp.blog.request.comment.CreateCommentRequest;
import com.ilhamyp.blog.request.comment.GetCommentRequest;
import com.ilhamyp.blog.response.comment.CreateCommentResponse;
import com.ilhamyp.blog.response.comment.GetCommentResponse;
import com.ilhamyp.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public List<GetCommentResponse> getComments(@RequestParam(required = false) String postSlug,
                                                @RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                                @RequestParam(required = false, defaultValue = "10") Integer limit){
        GetCommentRequest request = GetCommentRequest.builder()
                .postSlug(postSlug)
                .pageNo(pageNo)
                .limit(limit)
                .build();
       return commentService.getComments(request);
    }

    @GetMapping("/{id}")
    public Comment getComment(@PathVariable Integer id){
        return commentService.getComment(id);
    }

    @PostMapping
    public CreateCommentResponse createComment(@Valid @RequestBody CreateCommentRequest comment){
        return commentService.createComment(comment);
    }


}
