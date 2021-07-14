package com.eteration.simplebanking.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@Table(name = "WITHDRAWAL_TRANSACTION")
@JsonTypeName("WithdrawalTransaction")
public class WithdrawalTransaction extends Transaction{

    public WithdrawalTransaction(Account account) {
        super(account);
    }

    public WithdrawalTransaction() {
        super();
    }

    public WithdrawalTransaction(double amount) {
        super(amount);
    }
}


