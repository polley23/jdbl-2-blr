package com.gfg.jdblblr2session8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class Api {
    @Autowired
   // JdbcTemplate jdbcTemplate;
    CarManager carManager;
    @Autowired
    ColourManager colourManager;

    @PostMapping("/api/car")
    ResponseEntity newCar(@RequestBody CarDTO car){
       // jdbcTemplate.update("insert into car (`cname`,`colour`) values (?,?) ", car.name, car.colourId);
        carManager.CreateCar(car);
        return ResponseEntity.ok(car.name);
    }

    @GetMapping("/api/car")
    ResponseEntity getCar(@RequestParam(name = "name", defaultValue = "") String name){
//        List<Map<String, Object>> result =  jdbcTemplate.queryForList("select * from car where cname = ? ",name);
//        Map<String, Object> carMap = result.get(0);
//        CarDTO car = new CarDTO(carMap.get("cname").toString(), Long.parseLong(carMap.get("colour").toString()));
        CarDTO carDTO = carManager.GetCar(name);
        return ResponseEntity.ok(carDTO);
    }

    @PostMapping("/api/colour/{colour}")
    ResponseEntity newColour(@PathVariable("colour") String colour){
        colourManager.CreateColour(colour);
        return ResponseEntity.ok(colour);
    }

}
