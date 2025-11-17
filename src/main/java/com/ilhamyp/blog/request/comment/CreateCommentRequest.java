package com.ilhamyp.blog.request.comment;

import com.ilhamyp.blog.entity.Post;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class CreateCommentRequest {
    @Size(min = 2, max = 100, message = "Minimum 2 Characters")
    @NotNull
    private String name;

    @Size(min = 2, max = 100, message = "Minimum 2 Characters")
    @NotNull
    @Email
    private String email;

    @NotNull
    private Post post;

    @Size(min = 2, max = 10_000, message = "Minimum 2 Characters")
    @NotNull
    private String body;

}
