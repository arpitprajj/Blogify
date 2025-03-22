package com.learn.springboot.controller;


import com.learn.springboot.config.AppConstant;
import com.learn.springboot.entities.Post;
import com.learn.springboot.payloads.ApiResponse;
import com.learn.springboot.payloads.PostDto;
import com.learn.springboot.payloads.PostResponse;
import com.learn.springboot.services.FileService;
import com.learn.springboot.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("$(project.image)")
    private String path;



    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){
        PostDto createPost=this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

    }

    //getBy user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
        List<PostDto>posts=this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }
    //get by category

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDto>posts=this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    //get all post

    @GetMapping("/posts")
    public ResponseEntity<PostResponse>getAllPosts(@RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false)
                                         Integer pageNumber, @RequestParam(value =AppConstant.PAGE_SIZE,defaultValue = "5",required = false) Integer pageSize
                             ,@RequestParam (value = "sortBy",defaultValue = AppConstant.SORT_BY,required = false)String sortBy,
                                                   @RequestParam(value = AppConstant.SORT_DIR,defaultValue = "asc",required = false)String sortDir){
        PostResponse postResponse=this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new  ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);

    }
    // get single post

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto>getPosById(@PathVariable Integer postId){
        PostDto post=this.postService.getPostById(postId);
        return new  ResponseEntity<PostDto>(post,HttpStatus.OK);

    }

    //delete post
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post is successfully deleted ",true);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        PostDto updatedpost=this.postService.updatePost(postDto,postId);
        return  new ResponseEntity<PostDto>(updatedpost,HttpStatus.OK);


    }
    //Search
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>>searchByTitle(@PathVariable String keywords){
        List<PostDto>result=this.postService.searchPosts(keywords);
        return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
    }

    //post image upload
    @PostMapping("/post/image/upload/{postId}")
    public  ResponseEntity<PostDto>uploadPostImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable Integer postId
            ) throws IOException {
        PostDto postDto=this.postService.getPostById(postId);
         String fileName=this.fileService.uploadImage(path,image);

         postDto.setImageName(fileName);
         PostDto updatedPost=this.postService.updatePost(postDto,postId);

         return  new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }

@GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
    InputStream resource=this.fileService.getResource(path,imageName);
    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    StreamUtils.copy(resource,response.getOutputStream());
}



}
