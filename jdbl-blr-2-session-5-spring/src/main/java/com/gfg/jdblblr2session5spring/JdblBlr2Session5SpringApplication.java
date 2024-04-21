package com.gfg.jdblblr2session5spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class JdblBlr2Session5SpringApplication {

	@Bean
	HelloWorld helloWorld(){
		return new HelloWorld();
	}

	public static void main(String[] args)
	{
		WebApplicationContext webApplicationContext = (WebApplicationContext) SpringApplication.run(JdblBlr2Session5SpringApplication.class, args);
		HelloWorld helloWorld = webApplicationContext.getBean(HelloWorld.class);
		helloWorld.print();

	}

}
