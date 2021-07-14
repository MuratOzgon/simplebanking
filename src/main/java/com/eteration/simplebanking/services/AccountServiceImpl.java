package com.eteration.simplebanking.services;

import com.eteration.simplebanking.controller.ResponsePojo;
import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.repositories.AccountRepo;
import com.eteration.simplebanking.repositories.TransactionRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;
    private final TransactionRepo transactionRepo;

    public AccountServiceImpl(AccountRepo accountRepo, TransactionRepo transactionRepo) {
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepo.findAccountByAccountNumber(accountNumber);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountRepo.findAll();
    }

    @Override
    public Account saveAccount(Account account) {
        account.setCreatedDate(LocalDateTime.now());
        return accountRepo.save(account);
    }

    @Override
    public ResponsePojo credit(String accountNumber, double amount) throws NegativeNumberException{
        if(amount < 0){
            throw new NegativeNumberException(amount);
        }
        Account account = accountRepo.findAccountByAccountNumber(accountNumber);
        account.setBalance(account.getBalance() + amount);
        accountRepo.save(account);
        Transaction transaction = new DepositTransaction(account);
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());
        transaction.setApprovalCode(UUID.randomUUID().toString());
        saveTransaction(transaction);
        return new ResponsePojo("OK",transaction.getApprovalCode());
    }

    @Override
    public ResponsePojo debit(String accountNumber, double amount) throws InsufficientBalanceException {
        Account account = accountRepo.findAccountByAccountNumber(accountNumber);
        if (account.getBalance() < amount){
            throw new InsufficientBalanceException();
        }
        account.setBalance(account.getBalance() - amount);
        accountRepo.save(account);
        Transaction transaction = new WithdrawalTransaction(account);
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());
        transaction.setApprovalCode(UUID.randomUUID().toString());
        saveTransaction(transaction);
        return new ResponsePojo("OK",transaction.getApprovalCode());

    }

    @Override
    public ResponsePojo phoneBillPayment(String payee,
                                         String phoneNumber,
                                         String accountNumber,
                                         double amount) throws InsufficientBalanceException {
        Account account = accountRepo.findAccountByAccountNumber(accountNumber);
        if (account.getBalance() < amount){
            throw new InsufficientBalanceException();
        }
        account.setBalance(account.getBalance() - amount);
        accountRepo.save(account);
        PhoneBillPaymentTransaction transaction = new PhoneBillPaymentTransaction(account);
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());
        transaction.setApprovalCode(UUID.randomUUID().toString());
        transaction.setPayee(payee);
        transaction.setPhoneNumber(phoneNumber);
        saveTransaction(transaction);
        return new ResponsePojo("OK",transaction.getApprovalCode());
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

}
