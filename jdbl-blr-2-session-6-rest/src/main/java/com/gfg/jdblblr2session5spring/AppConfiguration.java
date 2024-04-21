package com.gfg.jdblblr2session5spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfiguration {
//    @Bean
//    HelloWorld helloWorld(Printer printer){
//        return new HelloWorld(printer);
//    }
    @Bean
    Printer printer(){
        return new Printer();
    }

    @Bean
    HelloWorld helloWorld(Printer printer){
        HelloWorld helloWorld =  new HelloWorld();
        helloWorld.printer = printer;
        return helloWorld;
    }
}
