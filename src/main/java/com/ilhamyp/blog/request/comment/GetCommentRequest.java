package com.ilhamyp.blog.request.comment;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCommentRequest {
    private String postSlug;
    private Integer pageNo;
    private Integer limit;
}
