package com.gfg.jdblbr2session7;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Qualifier("deprecated")
public class CarV1 implements Vehicle {

    @Override
    public void Drive() {
        log.info("Old method");
    }
}
