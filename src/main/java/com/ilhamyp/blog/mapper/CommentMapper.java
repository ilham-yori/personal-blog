package com.ilhamyp.blog.mapper;

import com.ilhamyp.blog.entity.Comment;
import com.ilhamyp.blog.request.comment.CreateCommentRequest;

import com.ilhamyp.blog.response.comment.CreateCommentResponse;
import com.ilhamyp.blog.response.comment.GetCommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment mapFromCreateCommentRequest(CreateCommentRequest commentRequest);

    CreateCommentResponse mapToCreateCommentResponse(Comment comment);
    GetCommentResponse mapToGetCommentResponse(Comment comment);

}
