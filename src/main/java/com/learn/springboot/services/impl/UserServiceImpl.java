package com.learn.springboot.services.impl;

import com.learn.springboot.entities.user;
import com.learn.springboot.exceptions.*;
import com.learn.springboot.payloads.userDto;
import com.learn.springboot.repositories.UserRepo;
import com.learn.springboot.services.userservice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements userservice {
   @Autowired
   private UserRepo userRepo;

   @Autowired
   private ModelMapper modelMapper;

    @Override
    public userDto createuser(userDto userdto) {
       user userr=this.dtoToUser(userdto);
       user savedUser=this.userRepo.save(userr);
        return this.userToDto(savedUser);
    }

    @Override
    public userDto updateuser(userDto userdto, Integer userId) {
       user userr=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
       userr.setName(userdto.getName());
       userr.setEmail(userdto.getEmail());
       userr.setPassword(userdto.getPassword());
       userr.setAbout(userdto.getAbout());
      user updatedUser=this.userRepo.save(userr);
      userDto userdto1=this.userToDto(updatedUser);

       return userdto1;
    }

    @Override
    public userDto getUserbyId(Integer userId) {
        user userr=this.userRepo.findById(userId).orElseThrow(()->new  ResourceNotFoundException("user","Id",userId));

        return this.userToDto(userr);

    }

    @Override
    public List<userDto> getAllUsers() {
       List<user> users=this.userRepo.findAll();
       List<userDto> userdtos=users.stream().map(userr->this.userToDto(userr)).collect(Collectors.toList());
        return userdtos;
    }

    @Override
    public void deleteUser(Integer userId) {
       user userr= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","Id",userId));
       this.userRepo.delete((userr));
    }
    private user dtoToUser(userDto userdto){
        //user userr=new user();
//        userr.setId(userdto.getId());
//        userr.setName(userdto.getName());
//        userr.setEmail(userdto.getEmail());
//        userr.setPassword(userdto.getPassword());
//        userr.setAbout(userdto.getAbout());
        user userr=this.modelMapper.map(userdto,user.class);
        return userr;
    }
    public userDto userToDto(user userr){
        userDto userdto=this.modelMapper.map(userr,userDto.class);
//        userdto.setId(userr.getId());
//        userdto.setName(userr.getName());
//        userdto.setPassword(userr.getPassword());
//        userdto.setEmail(userr.getEmail());
//        userdto.setAbout(userr.getAbout());
        return userdto;
    }
}
