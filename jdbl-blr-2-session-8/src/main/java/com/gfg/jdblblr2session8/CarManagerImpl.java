package com.gfg.jdblblr2session8;

import com.gfg.jdblblr2session8.entity.Car;
import com.gfg.jdblblr2session8.entity.Colour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarManagerImpl implements CarManager{
    @Autowired
    CarRepository carRepository;
    @Autowired
    ColourRepository colourRepository;
    @Override
    public void CreateCar(CarDTO carDTO) {
        Colour colour = colourRepository.findById(carDTO.getColourId()).get();
        Car car = Car.builder()
                .name(carDTO.name)
                .colour(colour)
                .build();
        carRepository.save(car);
    }

    @Override
    public CarDTO GetCar(String name) {
        Car car = carRepository.findByName(name).get();
        return new CarDTO(car.name,car.colour.id);

    }
}
