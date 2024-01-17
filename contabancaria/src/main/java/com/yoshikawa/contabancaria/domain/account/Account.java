package com.yoshikawa.contabancaria.domain.account;

import com.yoshikawa.contabancaria.domain.user.StatusType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name="account")
@Table(name="account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account")
    private Long account;
    private String agency;
    private BigDecimal balance;
    private String document;
    @Enumerated(EnumType.STRING)
    private StatusType statusType;

    public Long getId() {
        return account;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}

