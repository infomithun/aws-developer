package com.aws.spring.controller;

import com.aws.spring.entity.Car;
import com.aws.spring.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public Car findCarById(@PathVariable int id) {
        return carRepository.findItemById(id);
    }

    @PostMapping
    public Car save(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @DeleteMapping("/{id}")
    public String deleteCarById(@PathVariable int id)   {
        return carRepository.deleteCar(id);
    }

}
