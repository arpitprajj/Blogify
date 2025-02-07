package com.learn.springboot.payloads;

import com.learn.springboot.entities.Category;
import com.learn.springboot.entities.Comment;
import com.learn.springboot.entities.user;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Integer postId;
    private String title;
    private String content;
    private String imageName;

    private Date addedDate;

    private CategoryDto category;
    private userDto userr;

    private Set<CommentDto>comments=new HashSet<>();



}
