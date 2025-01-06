package com.learn.springboot.repositories;

import com.learn.springboot.entities.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<user,Integer> {
}
