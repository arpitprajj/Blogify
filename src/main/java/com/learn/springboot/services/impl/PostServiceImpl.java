package com.learn.springboot.services.impl;

import com.learn.springboot.entities.Category;
import com.learn.springboot.entities.Post;
import com.learn.springboot.entities.user;
import com.learn.springboot.exceptions.ResourceNotFoundException;
import com.learn.springboot.payloads.PostDto;
import com.learn.springboot.payloads.PostResponse;
import com.learn.springboot.repositories.CategoryRepo;
import com.learn.springboot.repositories.PostRepo;
import com.learn.springboot.repositories.UserRepo;
import com.learn.springboot.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {


        user userr=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","user Id",userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category_Id",categoryId));

        Post post=this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUserr(userr);
        post.setCategory(category);

        Post newPost=this.postRepo.save(post);




        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost=this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDto.class);

    }

    @Override
    public void deletePost(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        this.postRepo.delete(post);

    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
       Sort sort=null;
       if(sortDir.equalsIgnoreCase("dsc")){
           sort=Sort.by(sortBy).descending();
       }
       else{
           sort=Sort.by(sortBy).ascending();
       }
        Pageable p=  PageRequest.of(pageNumber,pageSize,sort);
        Page<Post>pagepost=this.postRepo.findAll(p);

        List<Post>allPost=pagepost.getContent();
        List<PostDto>allPostDtos=allPost.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
         PostResponse postResponse=new PostResponse();
         postResponse.setContent(allPostDtos);
         postResponse.setPageNumber(pagepost.getNumber());
         postResponse.setPageSize(pagepost.getSize());
         postResponse.setTotalElements(pagepost.getTotalElements());
         postResponse.setTotalPages(pagepost.getTotalPages());
         postResponse.setLastPage(pagepost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","Post Id",postId));

        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
      Category cat=this.categoryRepo.findById((categoryId)).orElseThrow( ()->new ResourceNotFoundException("Category","Category_Id",categoryId));
      List<Post>posts=this.postRepo.findByCategory(cat);
      List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        user userr=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User Id",userId));
        List<Post> posts=this.postRepo.findByUserr(userr);
        List<PostDto>postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {

        List<Post>posts=this.postRepo.findByTitleContaining(keyword);
        List<PostDto>postDto=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDto;
    }
}
