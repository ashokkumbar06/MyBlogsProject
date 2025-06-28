package com.Blog.Payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CommentDto {
    private long id;
    private String name;
    private String email;
    private String body;
}
