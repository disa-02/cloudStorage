package com.cloudStorage.cloudStorage.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cloudStorage.cloudStorage.repository.UserRepository;
import com.cloudStorage.exception.ApiErrorException;

@Service
public class UserDetailServiceImp  implements UserDetailsService{

    private UserRepository userRepository;

    
    public UserDetailServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new ApiErrorException("User not found"));
    }
    
}
