package com.gfg.jdblbr2session7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CarDriver {
    @Qualifier("newMethod")
    @Autowired
    Vehicle vehicle;



//    CarDriver(Vehicle vehicle){
//        this.vehicle = vehicle;
//    }
    void drive(){
        vehicle.Drive();
    }
}
