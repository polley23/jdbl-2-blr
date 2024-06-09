package com.gfg.jdbl_2_user_service_major_project;


import com.gfg.jdbl_2_user_service_major_project.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api {
    @Autowired
    UserManager userManager;

    @PostMapping("/api/user")
    ResponseEntity registerUser(@RequestBody UserDto userDto){
        userManager.register(userDto.getEmail());
        return ResponseEntity.ok().build();
    }
}
