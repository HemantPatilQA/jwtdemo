package com.selflearning.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        if("javainuse".equals(username)){
            return new User("javainuse", "$2a$10$Lgt6xIk1N0lrLckJwa35FObBQT1hjqtgjfnQMexuVwIzUTRps5lTi", new ArrayList<>());
            //Username : javainuse, password : password
        }else{
            throw new UsernameNotFoundException("User not found with username : " + username);
        }
    }
}
