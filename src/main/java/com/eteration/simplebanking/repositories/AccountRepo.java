package com.eteration.simplebanking.repositories;

import com.eteration.simplebanking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findAccountByAccountNumber(String accountNumber);
}
