package com.learn.springboot.services.impl;

import com.learn.springboot.entities.Comment;
import com.learn.springboot.entities.Post;
import com.learn.springboot.exceptions.ResourceNotFoundException;
import com.learn.springboot.payloads.CommentDto;
import com.learn.springboot.repositories.CommentRepo;
import com.learn.springboot.repositories.PostRepo;
import com.learn.springboot.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","PostId",postId));
        Comment comment=this.modelMapper.map(commentDto,Comment.class);
        comment.setPost((post));
        Comment savedComment=this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment com=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Comment Id",commentId));
        this.commentRepo.delete(com);


    }
}
