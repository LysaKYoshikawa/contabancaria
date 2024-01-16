package com.yoshikawa.contabancaria.domain.account;

import com.yoshikawa.contabancaria.DTOs.AccountDTO;
import com.yoshikawa.contabancaria.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity(name="accounts")
@Table(name="accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Double agencia;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private StatusType statusType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public StatusType getStatusType() {
        return statusType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Double getAccounts() {
        return agencia;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public void setAgencia(Double agencia) { this.agencia = agencia;
    }

    public void setStatusType(StatusType statusType) { this.statusType = statusType;
    }


    public Account(AccountDTO data){
        this.agencia = data.agencia();
        this.balance = data.balance();
        this.statusType = data.statusType();
    }


}
