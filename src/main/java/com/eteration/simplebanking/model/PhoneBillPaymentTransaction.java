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
@Table(name = "PHONE_BILL_PAYMENT_TRANSACTION")
@JsonTypeName("PhoneBillPaymentTransaction")
public class PhoneBillPaymentTransaction extends Transaction{

    String phoneNumber;
    String payee;

    public PhoneBillPaymentTransaction(Account account) {
        super(account);
    }

    public PhoneBillPaymentTransaction() {

    }
}
