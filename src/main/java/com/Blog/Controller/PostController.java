package com.Blog.Controller;

import com.Blog.Payload.PostDto;
import com.Blog.Service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//http://localhost:8080/api/posts
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //http://localhost:8080/api/posts
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult result) {
        //Binding class will return any error occured it will return back to the post man
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/posts?pageNo=0&pageSizeSize=5
    //http://localhost:8080/api/posts?pageNo=0&pageSizeSize=5&sortBy=title
    //http://localhost:8080/api/posts?pageNo=0&pageSizeSize=5&sortBy=title&sortDir=ascORdesc
    @GetMapping
    public List<PostDto> listallposts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        List<PostDto> postdtos = postService.listAllPosts(pageNo, pageSize, sortBy, sortDir);
        return postdtos;
    }

    //http://localhost:8080/api/posts/id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id) {
        PostDto dto = postService.getPostBYId(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id) {
        postService.deleteById(id);
        return new ResponseEntity<>("Post is deleted", HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/id
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updateById(@PathVariable("id") long id, @RequestBody PostDto postDto) {
        PostDto dto = postService.updatePost(id, postDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}