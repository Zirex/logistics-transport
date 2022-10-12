package com.ingeneo.logistics.services.Impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Logic to get the user form the Database

        return new User("admin","$2a$12$D338V6Rab.ycS1aJFxTbsOfPSCHaw.m3GF/ODVCu8jKhm4EAImfm6",new ArrayList<>());
    }
}
