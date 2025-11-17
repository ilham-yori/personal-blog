package com.ilhamyp.blog.response.post;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostBySlugResponse {
    private String title;
    private String body;
    private String slug;

}
