package com.koder.car.service;

import com.koder.car.model.Booking;
import com.koder.car.model.Car;
import com.koder.car.model.Customer;
import com.koder.car.model.enums.BookingStatus;
import com.koder.car.model.enums.CarStatus;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CustomerService {
    Map<String, Customer> customerTable = new HashMap<>();
    @Autowired
    BookingService bookingService;
    @Autowired
    CarInventoryService carInventoryService;

    public void addCustomer(Customer customer) {
        customerTable.put(customer.getAccountId(), customer);
        log.info("Customer added: " + customer);
    }

    public Customer getCustomer(String accountId) {
        return customerTable.get(accountId);
    }

    public void deleteCustomer(String accountId) {
        customerTable.remove(accountId);
        log.info("Customer deleted");
    }

    public Customer getRandomCustomer() {
        Faker faker = new Faker();
        return getAllCustomers().get(faker.random().nextInt(customerTable.size()));
    }


    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerTable.values());
    }

    public void pickupCar(Booking booking) {
        booking.setStatus(BookingStatus.PICKED_UP);
        Car car = carInventoryService.getCar(booking.getCarId());
        car.setStatus(CarStatus.RUNNING);
        log.info("Customer picked the car: " + booking);
    }

    public void dropOffCar(Booking booking) {
        booking.dropOff();
        Car car = carInventoryService.getCar(booking.getCarId());
        car.setStatus(CarStatus.AVAILABLE);
//        log.info("Customer dropped off the car: " + booking);
    }

    public String getCustomerPaymentReport() {
        StringBuilder sb = new StringBuilder();
        List<Customer> allCustomers = getAllCustomers();
        for (Customer customer : allCustomers) {
            sb.append(customer.getPerson().getName());
            sb.append(" : ");
            sb.append(customer.getRoundedAmountPaid());
            sb.append("\n");
        }
        return sb.toString();
    }
}
