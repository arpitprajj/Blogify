package com.learn.springboot.services;

import com.learn.springboot.entities.user;
import com.learn.springboot.payloads.userDto;

import java.util.List;

public interface userservice {

    userDto createuser(userDto userr);
    userDto updateuser(userDto userr,Integer userId);
    userDto getUserbyId(Integer userId);
    List<userDto>getAllUsers();
    void deleteUser(Integer userId);

}
