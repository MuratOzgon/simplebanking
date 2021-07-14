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
@Table(name = "DEPOSIT_TRANSACTION")
@JsonTypeName("DepositTransaction")
public class DepositTransaction extends Transaction {

    public DepositTransaction(Account account) {
        super(account);
    }

    public DepositTransaction() {
        super();
    }

    public DepositTransaction(double amount) {
        super(amount);
    }
}
