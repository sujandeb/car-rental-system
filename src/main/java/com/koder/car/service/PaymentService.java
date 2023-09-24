package com.koder.car.service;

import com.koder.car.model.*;
import com.koder.car.model.enums.PaymentMode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentService {
    double revenue;

    @Autowired
    CarInventoryService carInventoryService;
    @Autowired
    CarOwnerService carOwnerService;
    @Autowired
    CustomerService customerService;

    public String getTotalRevenue() {
        return String.format("%.2f", revenue);
    }

    public void makePayment(Booking booking) {
        Payment payment = booking.getPayment();
        payment.makePayment(PaymentMode.CREDIT_CARD);
        double amount = payment.getAmount();
        revenue += amount;

        Customer customer = customerService.getCustomer(booking.getCustomerId());
        customer.addAmountPaid(amount);

        Car car = carInventoryService.getCar(booking.getCarId());
        CarOwner carOwner = carOwnerService.getCarOwner(car.getCarOwnerId());
        carOwner.addAmountEarned(amount);
        log.info("Payment made for amount: " + amount);
    }
}
