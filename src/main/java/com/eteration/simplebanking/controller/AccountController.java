package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.NegativeNumberException;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AccountController.BASE_URL)
public class AccountController {

    public static final String BASE_URL = "/account/v1";
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.findAllAccounts();
    }

    @GetMapping("/{accountNumber}")
    public Account getAccountById(@PathVariable String accountNumber) {
        return accountService.getAccountByAccountNumber(accountNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Account saveAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @PostMapping("/credit/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public ResponsePojo credit(@RequestBody AmountPojo amount,
                                 @PathVariable String accountNumber)
            throws NegativeNumberException {
        return accountService.credit(accountNumber, amount.amount);
    }

    @PostMapping("/debit/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public ResponsePojo debit(@RequestBody AmountPojo amount,
                         @PathVariable String accountNumber)
            throws InsufficientBalanceException {
        return accountService.debit(accountNumber, amount.amount);
	}

    @PostMapping("/phone/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public ResponsePojo phoneBillPayment(@RequestBody AmountPojo amount,
                                    @PathVariable String accountNumber)
            throws InsufficientBalanceException {
        return accountService.phoneBillPayment(amount.payee,
                amount.phoneNumber,
                accountNumber,
                amount.amount);
    }

    @PostMapping("/transaction")
    @ResponseStatus(HttpStatus.OK)
    public Transaction saveTransaction(@RequestBody Transaction transaction) {
        return accountService.saveTransaction(transaction);
    }

}