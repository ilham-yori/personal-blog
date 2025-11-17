package com.ilhamyp.blog.mapper;

import com.ilhamyp.blog.entity.Post;
import com.ilhamyp.blog.request.post.CreatePostRequest;
import com.ilhamyp.blog.response.post.CreatePostResponse;
import com.ilhamyp.blog.response.post.GetPostResponse;
import com.ilhamyp.blog.response.post.UpdatePostBySlugResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post mapFromCreatePostRequest(CreatePostRequest postRequest);
    CreatePostResponse mapToCreatePostResponse(Post post);
    GetPostResponse mapToGetPostResponse(Post post);
    UpdatePostBySlugResponse mapToUpdatePostBySlugResponse(Post post);
}
