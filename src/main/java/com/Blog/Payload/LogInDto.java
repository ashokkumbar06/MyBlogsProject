package com.Blog.Payload;

import lombok.Data;

@Data
public class LogInDto {

    private String usernameOrEmail;

    private String password;

}