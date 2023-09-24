package com.koder.car.model;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Review {
    String reviewId;
    int rating;
    String comment;
    String customerId;
    Date reviewDate;

    public Review(int rating, String comment, String customerId) {
        this.reviewId = UUID.randomUUID().toString();
        this.rating = rating;
        this.comment = comment;
        this.customerId = customerId;
        this.reviewDate = new Date();
    }
}
