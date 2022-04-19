package com.selflearning.service;

import com.selflearning.dao.UserDao;
import com.selflearning.model.DAOUser;
import com.selflearning.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        //Commented old code and added below code for User Authentication using MySql JPA change
        DAOUser user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
        /*if("javainuse".equals(username)){
            return new User("javainuse", "$2a$10$Lgt6xIk1N0lrLckJwa35FObBQT1hjqtgjfnQMexuVwIzUTRps5lTi", new ArrayList<>());
            //Username : javainuse, password : password
        }else{
            throw new UsernameNotFoundException("User not found with username : " + username);
        }*/
    }

    //Added below method for User Authentication using MySql JPA change
    public DAOUser save(UserDTO user) {
        DAOUser newUser = new DAOUser();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDao.save(newUser);
    }

}
