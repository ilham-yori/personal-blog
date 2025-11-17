package com.ilhamyp.blog.request.post;
import jakarta.validation.Valid;
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
public class CreatePostRequest {
    @Size(min = 2, max = 100, message = "Minimum 2 Characters")
    @NotNull
    private String title;
    @Size(min = 2, message = "Minimum 2 Characters")
    @NotNull
    private String body;
    @Size(min = 2, max = 100, message = "Minimum 2 Characters")
    @NotNull
    private String slug;
}
