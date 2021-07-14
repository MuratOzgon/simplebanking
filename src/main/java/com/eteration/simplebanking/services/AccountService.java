package com.eteration.simplebanking.services;

import com.eteration.simplebanking.controller.ResponsePojo;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.NegativeNumberException;
import com.eteration.simplebanking.model.Transaction;

import java.util.List;

public interface AccountService {

    Account getAccountByAccountNumber(String accountNumber);

    List<Account>  findAllAccounts();

    Account saveAccount(Account account);

    ResponsePojo credit(String accountNumber, double amount) throws NegativeNumberException;

    ResponsePojo debit(String accountNumber, double amount) throws InsufficientBalanceException;

    ResponsePojo phoneBillPayment(String payee,String phoneNumber,String accountNumber, double amount) throws InsufficientBalanceException;

    Transaction saveTransaction(Transaction transaction);
}
