package com.koder.car.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    String streetAddress;
    String city;
    String state;
    String zipCode;
    String country;
    String latitude;
    String longitude;
}
