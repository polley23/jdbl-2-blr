package com.gfg.jdblblr2session5spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
public class HelloWorld {
    void print(){
        log.info("Hello World");
    }
}
