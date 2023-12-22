package com.cloudStorage.cloudStorage.mapper;

import org.springframework.stereotype.Component;

import com.cloudStorage.cloudStorage.dto.in.UserInDto;
import com.cloudStorage.cloudStorage.entity.User;

@Component
public class UserInDtoToUser implements IMapper<UserInDto,User>{

    @Override
    public User map(UserInDto in) {
        User user = new User();
        user.setUsername(in.getUsername());
        user.setEmail(in.getEmail());
        user.setPassword(in.getPassword());
        return user;
    }
    
}
