package com.gfg.jdblbr2session7;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Qualifier("newMethod")
public class CarV2 implements Vehicle {

    @Override
    public void Drive() {
        log.info("Current method");
    }
}
