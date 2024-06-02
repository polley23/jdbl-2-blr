package com.gfg.jdbl_2_auth_service_eureka;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "JDBL-2-USER-SERVICE-EUREKA")
public interface UserManagementClient {
    @GetMapping("/user")
    ResponseEntity<String> getUser();
}
