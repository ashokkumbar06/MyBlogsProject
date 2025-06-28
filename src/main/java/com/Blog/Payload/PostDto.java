package com.Blog.Payload;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private long id;

    @NotEmpty
    @Size(min = 2, message = "Title Should be more than 2 character's")
    private String title;

    private String description;

    private String content;
}