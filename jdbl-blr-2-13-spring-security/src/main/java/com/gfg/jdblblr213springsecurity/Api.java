package com.gfg.jdblblr213springsecurity;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api {
    @GetMapping("/home")
    String getHome(){
        return "You are in the home page";
    }

    @GetMapping("/api/secure")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    String getSecure(){
        return "You are in the secure page";
    }

    @GetMapping("/api/secret")
    @PreAuthorize("hasAnyRole('ADMIN')")
    String getSecret(){
        return "You are in the secret page";
    }
}
