package com.Blog.Controller;

import com.Blog.Payload.CommentDto;
import com.Blog.Payload.PostDto;
import com.Blog.Service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //http://localhost:808/api/posts/1/comments
    @PostMapping("/posts/{postid}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable(value = "postid") long postid,
            @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postid, commentDto), HttpStatus.CREATED);
    }

    //http://localhost:8080/api/posts/postid
    @GetMapping("/posts/{postid}/comments")
    public List<CommentDto> getcommentsByPost(
            @PathVariable(value = "postid") long postid) {
        return commentService.getCommnetsByPostId((postid));
    }

    //http://localhost:8080/api/posts/id
    @GetMapping("/posts/{postid}/comments/{commentid}")
    public ResponseEntity<CommentDto> getcomments(
            @PathVariable(value = "postid") long postid,
            @PathVariable(value = "commentid") Long commentid) {
        CommentDto commentdto = commentService.getCommentById(postid, commentid);
        return new ResponseEntity<>(commentdto, HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/id
    @PutMapping("/posts/{postid}/comments/{commentid}")
    public ResponseEntity<CommentDto> updateById(
            @PathVariable("postid") long postid,
            @PathVariable("commentid") long commentid,
            @RequestBody CommentDto commentDto) {
        CommentDto Udto = commentService.updatePost(postid, commentid, commentDto);
        return new ResponseEntity<>(Udto, HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/id
    @DeleteMapping("/posts/{postid}/comments/{commentid}")
    public ResponseEntity<String> deleteBycommentId(
            @PathVariable("postid") long postid,
            @PathVariable("commentid") long commentid
    ) {
        commentService.deleteBycommentId(postid, commentid);
        return new ResponseEntity<>("Comment id Deleted", HttpStatus.OK);
    }
}