package com.koder.car.model;

import com.koder.car.model.enums.PaymentMode;
import com.koder.car.model.enums.PaymentStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Payment {
    String paymentId;
    PaymentMode mode;
    PaymentStatus status;
    LocalDate paymentDate;
    double amount;

    public Payment(double amount) {
        this.paymentId = UUID.randomUUID().toString();
        this.status = PaymentStatus.PENDING;
        this.amount = amount;
    }

    public void makePayment(PaymentMode mode) {
        // make payment
        this.mode = mode;
        this.paymentDate = LocalDate.now();
        this.status = PaymentStatus.PAID;
    }
}
