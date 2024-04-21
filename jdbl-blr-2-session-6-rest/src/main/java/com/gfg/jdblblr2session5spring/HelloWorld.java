package com.gfg.jdblblr2session5spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class HelloWorld {
    @Autowired
    Printer printer;

//    HelloWorld(Printer printer){
//        this.printer = printer;
//
//    }
    HelloWorld(){

    }
    void print(){
       printer.print();
    }

    public void SetPrinter(Printer printer){
        this.printer = printer;
    }
}
