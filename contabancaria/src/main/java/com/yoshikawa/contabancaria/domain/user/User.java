package com.yoshikawa.contabancaria.domain.user;


import com.yoshikawa.contabancaria.DTOs.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name="users")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public String getEmail() {return email;}

    public UserType getUserType() {
        return userType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User(UserDTO data){
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.address = data.address();
        this.document = data.document();
        this.email = data.email();
        this.balance = data.balance();
        this.userType = data.userType();
        this.password = data.password();
    }

}
