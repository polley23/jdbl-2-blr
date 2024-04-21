package com.gfg.jdblblr2session5spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class Api {

    String user;

    @PostMapping("/api/user/{name}")
    ResponseEntity createUser(@PathVariable("name") String name){
        log.info("user " + name+ " is created");
        user = name;
        return ResponseEntity.ok().body(name);
    }

    @GetMapping("/api/user")
    ResponseEntity getUser(){
        return ResponseEntity.ok(user);
    }

    @PutMapping("/api/user/newName/{newName}")
    ResponseEntity updateUser(@PathVariable("newName") String name){
        user = name;
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/user/")
    ResponseEntity deleteUser(){
        user = "";
        return ResponseEntity.ok().build();
    }
}
