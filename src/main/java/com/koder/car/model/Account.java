package com.koder.car.model;


import com.koder.car.model.enums.AccountStatus;
import lombok.Data;

@Data
public abstract class Account {
    String accountId;
    String username;
    String password;
    AccountStatus status;
    Person person;
}
