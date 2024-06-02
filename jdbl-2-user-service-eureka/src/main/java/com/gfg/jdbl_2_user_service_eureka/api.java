package com.gfg.jdbl_2_user_service_eureka;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class api {
    @GetMapping("/user")
    ResponseEntity<String> getUser(){
        return ResponseEntity.ok("User is gfg");
    }
}
