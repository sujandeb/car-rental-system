package com.koder.car.service;

import com.koder.car.model.Address;
import com.koder.car.model.Booking;
import com.koder.car.model.Car;
import com.koder.car.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class BookingService {
    Map<String, Booking> bookingTable = new HashMap<>();

    @Autowired
    RentalRateService rentalRateService;

    private void insertBooking(Booking booking) {
        bookingTable.put(booking.getBookingId(), booking);
    }

    public void createBooking(Customer customer, Car car, LocalDate pickupDate, LocalDate dropOffDate) {
        Address location = car.getLocation();
        double rentalRate = rentalRateService.getRentalRate(car, pickupDate, dropOffDate);
        Booking booking = new Booking(car.getCarId(), customer.getAccountId(), rentalRate, pickupDate, dropOffDate, location, location);
        customer.addBooking(booking);
        car.addBooking(booking);
        insertBooking(booking);
        log.info("Booking made: " + booking);
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookingTable.values());
    }
}
