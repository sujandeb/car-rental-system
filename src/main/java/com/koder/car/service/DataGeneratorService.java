package com.koder.car.service;

import com.koder.car.model.*;
import com.koder.car.model.enums.AccountStatus;
import com.koder.car.model.enums.CarStatus;
import com.koder.car.model.enums.CarType;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class DataGeneratorService {
    Faker faker = new Faker();

    public Customer generateCustomer() {
        log.info("Customer generated");
        return new Customer(faker.name().username(),
                faker.internet().password(),
                generatePerson(),
                faker.number().digits(10));
    }

    public Person generatePerson() {
        log.info("Person generated");
        return new Person(
                faker.name().name(),
                generateAddress(),
                faker.internet().emailAddress(),
                faker.phoneNumber().phoneNumber());
    }

    public Address generateAddress() {
        return new Address(
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().state(),
                faker.address().zipCode(),
                faker.address().country(),
                faker.address().latitude(),
                faker.address().longitude());
    }

    public CarOwner generateCarOwner(int carCount) {
        CarOwner carOwner = new CarOwner(
                faker.name().username(),
                faker.internet().password(),
                AccountStatus.ACTIVE,
                generatePerson());
        for (int i = 0; i < carCount; i++) {
            carOwner.addCar(generateCar(carOwner));
        }
        return carOwner;
    }

    public Car generateCar(CarOwner carOwner) {
        return new Car(
                carOwner.getAccountId(),
                faker.vehicle().licensePlate(),
                faker.number().numberBetween(2, 7),
                faker.vehicle().manufacturer(),
                faker.vehicle().model(),
                faker.number().numberBetween(2000, 2023),
                faker.color().name(),
                CarType.SEDAN,
                CarStatus.AVAILABLE,
                generateAddress());
    }

    public LocalDate generatePickupDate() {
        LocalDate localDate = LocalDate.now();
        return localDate.plusDays(faker.number().numberBetween(2, 10));
    }

    public LocalDate generateDropOffDate() {
        LocalDate localDate = LocalDate.now();
        return localDate.plusDays(faker.number().numberBetween(11, 30));
    }
}
