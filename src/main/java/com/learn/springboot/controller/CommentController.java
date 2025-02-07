package com.learn.springboot.controller;


import com.learn.springboot.entities.Comment;
import com.learn.springboot.payloads.ApiResponse;
import com.learn.springboot.payloads.CommentDto;
import com.learn.springboot.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto>createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){

        CommentDto createdComment=this.commentService.createComment(commentDto,postId);
        return  new ResponseEntity<CommentDto>(createdComment, HttpStatus.CREATED);

    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse>deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted Successfully",true),HttpStatus.OK);
    }
}
