package com.ilhamyp.blog.service;

import com.ilhamyp.blog.entity.Comment;
import com.ilhamyp.blog.entity.Post;
import com.ilhamyp.blog.repository.CommentRepository;
import com.ilhamyp.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    public Iterable<Comment> getComments(String postSlug,
                                         Integer pageNo,
                                         Integer limit){
        Post post = postRepository.findBySlugAndIsDeleted(postSlug, false).orElse(null);
        if (post == null){
            return null;
        }
        PageRequest pageRequest = PageRequest.of(pageNo, limit);
        return commentRepository.findByPostId(post.getId(), pageRequest).getContent();
    }

    public Comment getComment(Integer id){
        return commentRepository.findById(id).orElse(null);
    }

    @Transactional
    public Comment createComment(Comment comment){
        Post post = postRepository.findBySlugAndIsDeleted(comment.getPost().getSlug(), false).orElse(null);
        if (post == null){
            return null;
        }

        comment.setCreatedAt(Instant.now().getEpochSecond());
        comment.getPost().setId(post.getId());
        comment = commentRepository.save(comment);

        post.setCommentCount(post.getCommentCount()+1);
        postRepository.save(post);

        return comment;
    }
}
