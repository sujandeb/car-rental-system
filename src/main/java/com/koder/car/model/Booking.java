package com.koder.car.model;

import com.koder.car.model.enums.BookingStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Data
public class Booking {
    String bookingId;
    BookingStatus status;
    LocalDate bookingTime;
    LocalDate pickupTime;
    LocalDate dropOffTime;
    Address pickupLocation;
    Address dropLocation;
    int distance;
    String carId;
    String customerId;
    double rentalRatePerHour;
    Payment payment;

    public Booking(String carId, String customerId, double rentalRatePerHour, LocalDate pickupTime, LocalDate dropOffTime,
                   Address pickupLocation, Address dropLocation) {
        this.bookingId = UUID.randomUUID().toString();
        this.status = BookingStatus.CONFIRMED;
        this.bookingTime = LocalDate.now();
        this.pickupTime = pickupTime;
        this.dropOffTime = dropOffTime;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.carId = carId;
        this.customerId = customerId;
        this.rentalRatePerHour = rentalRatePerHour;
    }

    public void dropOff() {
        this.status = BookingStatus.DROPPED_OFF;
        // generate a payment invoice
        double amount = calculatePaymentAmount();
        this.payment = new Payment(amount);
    }

    private double calculatePaymentAmount() {
        long days = ChronoUnit.DAYS.between(pickupTime, dropOffTime);
        return days * 24 * rentalRatePerHour;
    }
}
