package com.gfg.jdblblr2librarymanagement.manager;

import com.gfg.jdblblr2librarymanagement.entity.Fine;
import com.gfg.jdblblr2librarymanagement.entity.User;
import com.gfg.jdblblr2librarymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagerImpl implements UserManager{
    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(String username) {
        Fine fine = Fine.builder()
                .amount(0)
                .build();
        User user = User.builder()
                .username(username)
                .fine(fine)
                .build();
        userRepository.save(user);
    }
}
