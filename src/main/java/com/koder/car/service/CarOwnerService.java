package com.koder.car.service;

import com.koder.car.model.CarOwner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CarOwnerService {
    Map<String, CarOwner> carOwners = new HashMap<>();

    @Autowired
    CarInventoryService carInventoryService;

    public void addCarOwner(CarOwner carOwner) {
        carOwners.put(carOwner.getAccountId(), carOwner);
        log.info("Car owner added" + carOwner);
        carInventoryService.addCars(carOwner.getCars());
    }

    public CarOwner getCarOwner(String accountId) {
        return carOwners.get(accountId);
    }

    public void deleteCarOwner(String accountId) {
        carOwners.remove(accountId);
    }

    public List<CarOwner> getAllCarOwners() {
        return new ArrayList<>(carOwners.values());
    }

    public String getOwerRevenueReport() {
        StringBuilder sb = new StringBuilder();
        List<CarOwner> allCarOwners = getAllCarOwners();
        for (CarOwner carOwner : allCarOwners) {
            sb.append(carOwner.getPerson().getName());
            sb.append(" : ");
            sb.append(carOwner.getRoundedAmountEarned());
            sb.append("\n");
        }
        return sb.toString();
    }

}
