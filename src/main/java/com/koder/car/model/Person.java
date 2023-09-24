package com.koder.car.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Person {
    String personId;
    String name;
    Address address;
    String email;
    String phone;

    public Person(String name, Address address, String email, String phone) {
        this.personId = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
}
