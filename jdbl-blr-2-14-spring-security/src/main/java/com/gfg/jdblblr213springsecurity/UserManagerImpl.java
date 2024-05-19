package com.gfg.jdblblr213springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

public class UserManagerImpl implements UserDetailsService, UserManager {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public void AddUser(UserDto userDto) {
        MyRoles roles = MyRoles.builder().role(userDto.role).build();
        MyUser user = MyUser
                .builder()
                .username(userDto.username)
                .password(passwordEncoder.encode(userDto.password))
                .roles(Arrays.asList(roles))
                .build();
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUser myUser = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("username not found")
        );

        UserDetails user = User.builder()
                .roles(myUser.getRoles().get(0).getRole())
                .username(myUser.username)
                .password(myUser.password)
                .build();

        return user;
    }
}
