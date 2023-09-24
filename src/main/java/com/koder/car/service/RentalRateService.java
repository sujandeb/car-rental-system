package com.koder.car.service;

import com.koder.car.model.Car;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RentalRateService {

    /**
     * This method returns the rental rate of a car based on various factors
     *
     * @param car
     * @return
     */
    public double getRentalRate(Car car, LocalDate startDate, LocalDate endDate) {
        // to simplify, we are returning a random number from $1 to $20
        Faker faker = new Faker();
        return faker.number().randomDouble(2, 1, 5);
    }
}
