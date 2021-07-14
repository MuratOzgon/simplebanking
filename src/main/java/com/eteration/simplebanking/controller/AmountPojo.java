package com.eteration.simplebanking.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AmountPojo {
    double amount;
    String phoneNumber;
    String payee;

    public AmountPojo(double amount) {
        this.amount = amount;
    }
}
