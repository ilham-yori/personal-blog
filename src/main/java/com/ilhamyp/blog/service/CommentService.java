package com.ilhamyp.blog.service;

import com.ilhamyp.blog.entity.Comment;
import com.ilhamyp.blog.entity.Post;
import com.ilhamyp.blog.exception.ApiException;
import com.ilhamyp.blog.mapper.CommentMapper;
import com.ilhamyp.blog.repository.CommentRepository;
import com.ilhamyp.blog.repository.PostRepository;
import com.ilhamyp.blog.request.comment.CreateCommentRequest;
import com.ilhamyp.blog.request.comment.GetCommentRequest;
import com.ilhamyp.blog.response.comment.CreateCommentResponse;
import com.ilhamyp.blog.response.comment.GetCommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    public List<GetCommentResponse> getComments(GetCommentRequest request){
        Post post = postRepository.findBySlugAndIsDeleted(request.getPostSlug(), false)
                .orElseThrow(() -> new ApiException("Not Found", HttpStatus.NOT_FOUND));
        PageRequest pageRequest = PageRequest.of(request.getPageNo(), request.getLimit());
        List<Comment> comments = commentRepository.findByPostId(post.getId(), pageRequest).getContent();
        List<GetCommentResponse> responses = new ArrayList<>();
        comments.forEach(comment -> responses.add(CommentMapper.INSTANCE.mapToGetCommentResponse(comment)));
        return responses;
    }

    public Comment getComment(Integer id){
        return commentRepository.findById(id).orElse(null);
    }

    @Transactional
    public CreateCommentResponse createComment(CreateCommentRequest request){
        Post post = postRepository.findBySlugAndIsDeleted(request.getPost().getSlug(), false)
                .orElseThrow(() -> new ApiException("Not Found", HttpStatus.NOT_FOUND));

        Comment comment = CommentMapper.INSTANCE.mapFromCreateCommentRequest(request);

        comment.setCreatedAt(Instant.now().getEpochSecond());
        comment.getPost().setId(post.getId());
        commentRepository.save(comment);

        post.setCommentCount(post.getCommentCount()+1);
        postRepository.save(post);

        return CommentMapper.INSTANCE.mapToCreateCommentResponse(comment);
    }
}
