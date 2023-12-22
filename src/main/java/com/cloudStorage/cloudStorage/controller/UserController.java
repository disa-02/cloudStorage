package com.cloudStorage.cloudStorage.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudStorage.cloudStorage.dto.in.UserInDto;
import com.cloudStorage.cloudStorage.entity.User;
import com.cloudStorage.cloudStorage.mapper.UserInDtoToUser;
import com.cloudStorage.cloudStorage.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserInDtoToUser userInDtoToUser;
    private UserService userService;

    public UserController(UserInDtoToUser userInDtoToUser,UserService userService){
        this.userInDtoToUser = userInDtoToUser;
        this.userService = userService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UserInDto userDto) {
        User user = userInDtoToUser.map(userDto);
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
