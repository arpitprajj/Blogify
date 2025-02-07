package com.learn.springboot.services;

import com.learn.springboot.entities.Post;
import com.learn.springboot.payloads.PostDto;
import com.learn.springboot.payloads.PostResponse;

import java.util.List;

public interface PostService {

    //create

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    //update

    PostDto updatePost(PostDto postDto,Integer postId);

    //delete

    void deletePost(Integer postId);

    //get all post

    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    //get single post

    PostDto getPostById(Integer postId);

    //get all post by category

    List<PostDto> getPostByCategory(Integer categoryId);

    //get all post by user

    List<PostDto>getPostByUser(Integer userId);

    //search post

    List<PostDto>searchPosts(String keyword);




}
