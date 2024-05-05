package com.gfg.jdblblr2librarymanagement.manager;

import com.gfg.jdblblr2librarymanagement.entity.Fine;
import com.gfg.jdblblr2librarymanagement.entity.User;
import com.gfg.jdblblr2librarymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FineManageImpl implements FineManager{
    @Autowired
    UserRepository userRepository;

    @Override
    public Integer getForUser(String username) throws Exception {
        User user =  userRepository.findByUsername(username).orElseThrow(
                ()-> new Exception("No such user exists.")
        );
        return user.getFine().getAmount();
    }

    @Override
    public void payForUser(String username, Integer amount) throws Exception {
        User user =  userRepository.findByUsername(username).orElseThrow(
                ()-> new Exception("No such user exists.")
        );
        Fine fine = user.getFine();
        Integer presentAmount = fine.getAmount();
        fine.setAmount(Math.max(0,presentAmount-amount));
        userRepository.save(user);
    }
}
