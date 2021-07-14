package com.eteration.simplebanking.model;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException() {
        super("Account does not have sufficient balance for this transaction");
    }
}
