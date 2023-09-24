package com.koder.car.model;

import com.koder.car.model.enums.CarStatus;
import com.koder.car.model.enums.CarType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Car {
    String carId;
    String carOwnerId;
    String plateNumber;
    int seatCount;
    String manufacturer;
    String model;
    int year;
    String color;
    CarType type;
    CarStatus status;
    Address location;
    List<Review> reviews;
    List<CarLog> carLogs;
    List<Booking> bookings;

    public Car(String carOwnerId, String plateNumber, int seatCount, String manufacturer, String model, int year, String color, CarType type, CarStatus status, Address location) {
        this.carId = UUID.randomUUID().toString();
        this.carOwnerId = carOwnerId;
        this.plateNumber = plateNumber;
        this.seatCount = seatCount;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.color = color;
        this.type = type;
        this.status = status;
        this.location = location;
        this.reviews = new ArrayList<>();
        this.carLogs = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void addCarLog(CarLog carLog) {
        carLogs.add(carLog);
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
}
