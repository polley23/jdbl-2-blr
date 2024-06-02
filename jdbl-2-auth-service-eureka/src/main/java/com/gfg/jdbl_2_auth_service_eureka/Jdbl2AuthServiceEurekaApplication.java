package com.gfg.jdbl_2_auth_service_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Jdbl2AuthServiceEurekaApplication {

	public static void main(String[] args) {
		WebApplicationContext ioc = (WebApplicationContext) SpringApplication.run(Jdbl2AuthServiceEurekaApplication.class, args);
		manager manager = ioc.getBean(com.gfg.jdbl_2_auth_service_eureka.manager.class);
		manager.CallUserService();
	}

}
