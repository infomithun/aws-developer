package com.aws.spring.repository;

import com.aws.spring.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository {

    @Autowired
    private RedisTemplate redisTemplate;

    public static final String HASH_NAME = "CARS";

    /**
     * Saves Car object in CARS hashmap with id as key
     *
     * @param car Car Object
     * @return Saved Car Object
     */
    public Car save(Car car) {
        redisTemplate.opsForHash().put(HASH_NAME, car.getId(), car);
        return car;
    }

    /**
     * GET all the Car Items from Redis
     *
     * @return List of all available Cars
     */
    public List<Car> findAll() {
        return redisTemplate.opsForHash().values(HASH_NAME);
    }

    /**
     * GET Car object from CARS hashmap by id key
     *
     * @param id id of the Car
     * @return Car Object for the id
     */
    public Car findItemById(int id) {
        return (Car) redisTemplate.opsForHash().get(HASH_NAME, id);
    }

    /**
     * DELETE the hashkey by id from CARS hashmap
     *
     * @param id id of the car
     * @return Success message
     */
    public String deleteCar(int id) {
        redisTemplate.opsForHash().delete(HASH_NAME, id);
        return "Car with ID " + id + " deleted successfully";
    }
}
