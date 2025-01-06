package com.learn.springboot.controller;


import com.learn.springboot.payloads.ApiResponse;
import com.learn.springboot.payloads.userDto;
import com.learn.springboot.services.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private userservice userService;

    @PostMapping("/")
    public ResponseEntity<userDto>createUser(@RequestBody userDto userdto){
     userDto createUserDto=this.userService.createuser(userdto);
     return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }



    //post- create user
    //put - update user
    @PutMapping("/{userId}")
    public ResponseEntity<userDto>updateUser(@RequestBody userDto userdto,@PathVariable("userId") Integer uid){
        userDto updatedUser=this.userService.updateuser(userdto,uid);
    return ResponseEntity.ok(updatedUser);
    }
    //delete - delete user
@DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse>deleteUser(@PathVariable("userId") Integer uid){
   this.userService.deleteUser(uid);
   return new  ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
}
    // get- return user

    @GetMapping("/")
    public ResponseEntity<List<userDto>> getAllUser(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<userDto> getSingleUser(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserbyId(userId));
    }
}
