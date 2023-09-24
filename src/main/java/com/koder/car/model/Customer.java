package com.koder.car.model;

import com.koder.car.model.enums.AccountStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class Customer extends Account {
    String drivingLicense;
    List<Booking> bookings;
    double totalAmountPaid;

    public Customer(String username, String password, Person person, String drivingLicense) {
        this.accountId = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.status = AccountStatus.ACTIVE;
        this.person = person;
        this.bookings = new ArrayList<>();
        this.drivingLicense = drivingLicense;
    }

    public String getRoundedAmountPaid() {
        return String.format("%.2f", totalAmountPaid);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public boolean addBooking(Booking booking) {
        return bookings.add(booking);
    }

    public boolean removeBooking(Booking booking) {
        return bookings.remove(booking);
    }

    public void addAmountPaid(double amount) {
        this.totalAmountPaid += amount;
    }
}
