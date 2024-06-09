package com.gfg.jdbl_2_user_service_major_project;

import com.gfg.jdbl_2_user_service_major_project.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserManagerImpl implements UserManager{
    @Autowired
    UserRepository userRepository;
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void register(String email) {
        User user = User.builder().email(email).build();
        userRepository.save(user);
        kafkaTemplate.send("user-jdbl-2",email);
    }
}
