package com.eteration.simplebanking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@Table(name = "TRANSACTION")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DepositTransaction.class, name = "DepositTransaction"),
        @JsonSubTypes.Type(value = WithdrawalTransaction.class, name = "WithdrawalTransaction"),
        @JsonSubTypes.Type(value = PhoneBillPaymentTransaction.class, name = "PhoneBillPaymentTransaction")
})
public abstract class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private LocalDateTime date;
    private double amount;
    private String approvalCode;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Transaction(Account account){
        this.account = account;
    }

    public Transaction(double amount) {
        this.amount = amount;
    }

    public Transaction() {

    }
}
