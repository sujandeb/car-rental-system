package com.koder.car.model;

import com.koder.car.model.enums.CarLogType;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class CarLog {
    String carLogId;
    CarLogType carLogType;
    String description;
    Date createdOn;

    public CarLog(CarLogType carLogType, String description) {
        this.carLogId = UUID.randomUUID().toString();
        this.carLogType = carLogType;
        this.description = description;
        this.createdOn = new Date();
    }
}
