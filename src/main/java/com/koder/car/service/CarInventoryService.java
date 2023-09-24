package com.koder.car.service;

import com.koder.car.model.Car;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarInventoryService {
    Map<String, Car> carTable = new HashMap<>();

    public void addCar(Car car) {
        carTable.put(car.getCarId(), car);
    }

    public void addCars(List<Car> cars) {
        for (Car car : cars) {
            addCar(car);
        }
    }

    public Car getRandomCar() {
        Faker faker = new Faker();
        return getAllCars().get(faker.random().nextInt(carTable.size()));
    }

    public void removeCar(Car car) {
        carTable.remove(car.getCarId());
    }

    public Car getCar(String carId) {
        return carTable.get(carId);
    }

    public List<Car> getAllCars() {
        return new ArrayList<>(carTable.values());
    }
}
