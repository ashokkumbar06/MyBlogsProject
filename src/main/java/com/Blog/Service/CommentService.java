package com.Blog.Service;

import com.Blog.Payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postid, CommentDto commentDto);

    List<CommentDto> getCommnetsByPostId(long postId);

    CommentDto getCommentById(Long postid, Long commentid);

    CommentDto updatePost(long postid, long commentid, CommentDto commentDto);

    void deleteBycommentId(long postid, long commentid);

}