package com.yoshikawa.contabancaria.domain.user;


import com.yoshikawa.contabancaria.DTOs.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.usertype.UserType;

import java.math.BigDecimal;

@Entity(name="users")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    @Column(unique = true)
    private String agencia;
    @Column(unique = true)
    private String address;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private StatusType statusType;

    public String getEmail() {return email;}

    public StatusType getStatusType() {
        return statusType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User(UserDTO data){
        this.firstName = data.firstName();
        this.agencia = data.agencia();
        this.address = data.address();
        this.document = data.document();
        this.email = data.email();
        this.balance = data.balance();
        this.statusType = data.statusType();
        this.password = data.password();
    }

}
