package com.Blog.Service.Impl;

import com.Blog.Entity.Comment;
import com.Blog.Entity.Post;
import com.Blog.Exception.ResourceNotFoundException;
import com.Blog.Payload.CommentDto;
import com.Blog.Repositry.CommentRepository;
import com.Blog.Repositry.PostRepository;
import com.Blog.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepo;

    private PostRepository postRepo;

    private ModelMapper modleMapper;

    public CommentServiceImpl(CommentRepository commentRepo, PostRepository postRepo, ModelMapper modleMapper) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.modleMapper = modleMapper;
    }

    @Override
    public CommentDto createComment(long postid, CommentDto commentDto) {
        Post post = postRepo.findById(postid).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with Id" + postid)
        );
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment newComment = commentRepo.save(comment);
        CommentDto dto = mapToDTO(newComment);
        return dto;
    }

    @Override
    public List<CommentDto> getCommnetsByPostId(long postId) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post Not found with Id" + postId)
        );
        List<Comment> comments = commentRepo.findByPostId(postId);
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());

    }

    @Override
    public CommentDto getCommentById(Long postid, Long commentid) {
        Post post = postRepo.findById(postid).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with Id" + postid)
        );
        Comment comment = commentRepo.findById(commentid).orElseThrow(
                () -> new ResourceNotFoundException("Comment Not Found with Id :" + commentid)
        );
        return mapToDTO(comment);
    }

    @Override
    public CommentDto updatePost(long postid, long commentid, CommentDto commentDto) {
        Post post = postRepo.findById(postid).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with Id" + postid)
        );
        Comment comment = commentRepo.findById(commentid).orElseThrow(
                () -> new ResourceNotFoundException("Comment Not Found with Id :" + commentid)
        );
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment updateComment = commentRepo.save(comment);
        return mapToDTO(updateComment);
    }

    @Override
    public void deleteBycommentId(long postid, long commentid) {
        Post post = postRepo.findById(postid).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with Id" + postid));
        Comment comment = commentRepo.findById(commentid).orElseThrow(
                () -> new ResourceNotFoundException("Comment Not Found with Id :" + commentid));
        commentRepo.deleteById(commentid);
    }

    private CommentDto mapToDTO(Comment comment) {
        CommentDto commentDto = modleMapper.map(comment, CommentDto.class);
        return commentDto;
    }
    //manual Converting Entity.class to Dto.class
    //CommentDto commentDto = new CommentDto();
    //commentDto.setId(comment.getId());
    //commentDto.setName(comment.getName());
    //commentDto.setEmail(comment.getEmail());
    //commentDto.setBody(comment.getBody());


    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = modleMapper.map(commentDto, Comment.class);
        return comment;
    }
    //manual Converting  Dto.class to Entity.class
    // Comment comment = new Comment();
    // comment.setId(commentDto.getId());
    // comment.setName(commentDto.getName());
    // comment.setEmail(commentDto.getEmail());
    //comment.setBody(commentDto.getBody());
}