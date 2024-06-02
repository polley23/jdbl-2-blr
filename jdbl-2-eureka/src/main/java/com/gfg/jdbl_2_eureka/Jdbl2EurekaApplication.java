package com.gfg.jdbl_2_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Jdbl2EurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Jdbl2EurekaApplication.class, args);
	}

}
