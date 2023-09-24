package com.koder.car;


import com.koder.car.model.Booking;
import com.koder.car.model.Car;
import com.koder.car.model.Customer;
import com.koder.car.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
public class CarRentalSystem {
    @Autowired
    CustomerService customerService;
    @Autowired
    CarOwnerService carOwnerService;
    @Autowired
    CarInventoryService carInventoryService;
    @Autowired
    BookingService bookingService;
    @Autowired
    PaymentService paymentService;

    @Autowired
    private DataGeneratorService dataGeneratorService;

    @EventListener(ApplicationReadyEvent.class)
    public void runningCarRentalSystem() {
        setup();
    }

    private void setup() {
        log.info("Setting up the car rental system");
        // Create a few owners with 100 cars each
        createCarOwners(10, 20);
        // Create a few customers
        createCustomer(100);
        // Create a few cars
        runSimulation(200);
    }

    /**
     * This method runs the simulation of the car rental system
     * ============== MISSING FUNCTIONALITY ==================
     * The car search functionality is missing. The customer should be able to search for cars based on location, car type, etc.
     * when calling bookingService.createBooking() method, the car should be available for the given dates.
     *
     * @param customerCount
     */
    public void runSimulation(int customerCount) {
        for (int i = 0; i < customerCount; i++) {
            // find a random customer
            Customer customer = customerService.getRandomCustomer();
            // find a random car owner
            Car car = carInventoryService.getRandomCar();
            LocalDate pickupDate = dataGeneratorService.generatePickupDate();
            LocalDate dropOffDate = dataGeneratorService.generateDropOffDate();
            // customer make bookings
            bookingService.createBooking(customer, car, pickupDate, dropOffDate);
        }
        // customer pick up cars
        List<Booking> allBookings = bookingService.getAllBookings();
        for (Booking booking : allBookings) {
            customerService.pickupCar(booking);

        }
        // customer drop off cars
        // customer pay for cars
        for (Booking booking : allBookings) {
            customerService.dropOffCar(booking);
            paymentService.makePayment(booking);
        }
        // print amount paid by each customer
        log.info(customerService.getCustomerPaymentReport());
        // calculate revenue by each car owner
        log.info(carOwnerService.getOwerRevenueReport());
        // print the total revenue
        log.info("Total revenue: " + paymentService.getTotalRevenue());
    }

    private void createCarOwners(int ownerCount, int carCount) {
        for (int i = 0; i < ownerCount; i++) {
            carOwnerService.addCarOwner(dataGeneratorService.generateCarOwner(carCount));
        }
    }


    public void createCustomer(int count) {
        for (int i = 0; i < count; i++) {
            customerService.addCustomer(dataGeneratorService.generateCustomer());
        }
    }

}


