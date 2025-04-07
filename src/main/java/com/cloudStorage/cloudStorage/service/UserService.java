package com.cloudStorage.cloudStorage.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cloudStorage.cloudStorage.entity.Folder;
import com.cloudStorage.cloudStorage.entity.User;
import com.cloudStorage.cloudStorage.repository.UserRepository;
import com.cloudStorage.exception.ApiErrorException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void create(User user) {
        Optional<User> userOpt = userRepository.findByUsername(user.getUsername());
        if(userOpt.isPresent()){
            throw new ApiErrorException("The user is already exist");
        }

        Folder folder = new Folder();
        String id= UUID.randomUUID().toString();
        folder.setName("main_" + user.getUsername());
        folder.setUniqueId(id);
        user.setMainFolder(folder);
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userRepository.save(user);
    }

    public Optional<User> findById(Integer userId) {
        return userRepository.findById(userId);
    }

    
}
