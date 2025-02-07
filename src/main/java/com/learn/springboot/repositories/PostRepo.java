package com.learn.springboot.repositories;

import com.learn.springboot.entities.Category;
import com.learn.springboot.entities.Post;
import com.learn.springboot.entities.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUserr(user userr);
    List<Post>findByCategory(Category category);
    List<Post>findByTitleContaining(String title);

    //@Query("select p from Post p where p.title like: key")
    //List<Post>searchByTitle(@Param("key) String title);
}
