package com.gfg.jdbl_2_auth_service_eureka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class manager {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserManagementClient userManagementClient;

    public void CallUserService(){
//        ResponseEntity<String> responseEntity =
//                restTemplate.getForEntity("http://JDBL-2-USER-SERVICE-EUREKA/user", String.class);
//
        ResponseEntity<String> responseEntity =  userManagementClient.getUser();
        log.info(responseEntity.getBody());

    }
}
