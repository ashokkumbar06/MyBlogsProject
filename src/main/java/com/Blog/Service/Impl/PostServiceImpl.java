package com.Blog.Service.Impl;

import com.Blog.Entity.Post;
import com.Blog.Exception.ResourceNotFoundException;
import com.Blog.Payload.PostDto;
import com.Blog.Repositry.PostRepository;
import com.Blog.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper modleMapper;

    //This will create Bean
    public PostServiceImpl(PostRepository postRepository, ModelMapper modleMapper) {
        this.postRepository = postRepository;
        this.modleMapper = modleMapper;
    }

    @Override
    public PostDto getPostBYId(long id) {//custom Exception
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("POst Not Found with ID:" + id)
        );
        return maptoDto(post);
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post p = new Post();
        p.setTitle(postDto.getTitle());
        p.setDescription(postDto.getDescription());
        p.setContent(postDto.getContent());
        Post newPost = postRepository.save(p);

        PostDto dto = new PostDto();
        dto.setId(newPost.getId());
        dto.setTitle(newPost.getTitle());
        dto.setContent(newPost.getContent());
        dto.setDescription(newPost.getDescription());
        return dto;
    }

    @Override
    public List<PostDto> listAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        //OR
        // if(sortDir.equalsIgnoreCase("asc")){
        //  sort = sort.by(sortBy).ascending();
        // }else{
        //  sort = sort.by(sortBy).descending();

        // }
        // Sort sort = Sort.by(sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> listOfposts = postRepository.findAll(pageable);
        List<Post> posts = listOfposts.getContent();
        List<PostDto> postdtos = posts.stream().map(x -> maptoDto(x)).collect(Collectors.toList());
        return postdtos;
    }

    @Override
    public void deleteById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post Not Found with ID:" + id)
        );
        postRepository.deleteById(id);
    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post Not Found with ID:" + id)
        );
        Post newpost = maptoEntity(postDto);
        newpost.setId(id);
        Post updatedPost = postRepository.save(newpost);
        PostDto dto = maptoDto(updatedPost);
        return dto;
    }

    PostDto maptoDto(Post post) {
        PostDto dto = modleMapper.map(post, PostDto.class);
        return dto;
    }
    //manual Converting Entity.class to Dto.class
    /* PostDto dto = new PostDto();
      dto.setId(post.getId());
      dto.setTitle(post.getTitle());
      dto.setContent(post.getContent());
      dto.setDescription(post.getDescription());
      return dto;*/


    Post maptoEntity(PostDto postdto) {
        Post post = modleMapper.map(postdto, Post.class);
        return post;
    }
    //manual Converting  Dto.class to Entity.class
   /* Post post = new Post();
      post.setId(postdto.getId());
      post.setTitle(postdto.getTitle());
      post.setContent(postdto.getContent());
      post.setDescription(postdto.getDescription());*/
}