package com.koder.car.model;

import com.koder.car.model.enums.AccountStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class CarOwner extends Account {
    List<Car> cars;
    double revenue;

    public CarOwner(String username, String password, AccountStatus status, Person person) {
        this.username = username;
        this.accountId = UUID.randomUUID().toString();
        this.password = password;
        this.status = status;
        this.person = person;
        this.cars = new ArrayList<>();
    }

    public boolean addCar(Car car) {
        return cars.add(car);
    }

    public boolean removeCar(Car car) {
        return cars.remove(car);
    }

    public void addAmountEarned(double amountEarned) {
        this.revenue += amountEarned;
    }

    public String getRoundedAmountEarned() {
        return String.format("%.2f", revenue);
    }
}
