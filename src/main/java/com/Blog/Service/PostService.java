package com.Blog.Service;

import com.Blog.Payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto getPostBYId(long id);

    PostDto createPost(PostDto postDto);

    List<PostDto> listAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    void deleteById(long id);

    PostDto updatePost(long id, PostDto postdto);

}