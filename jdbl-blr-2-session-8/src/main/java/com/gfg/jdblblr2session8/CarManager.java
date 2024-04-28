package com.gfg.jdblblr2session8;

import com.gfg.jdblblr2session8.CarDTO;

public interface CarManager {
    void CreateCar(CarDTO carDTO);
    CarDTO GetCar(String name);
}
