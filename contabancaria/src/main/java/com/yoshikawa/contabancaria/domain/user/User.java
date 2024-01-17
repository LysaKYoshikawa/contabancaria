package com.yoshikawa.contabancaria.domain.user;


import com.yoshikawa.contabancaria.DTOs.UserDTO;
import com.yoshikawa.contabancaria.domain.account.Account;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.usertype.UserType;

import java.math.BigDecimal;
import java.util.List;

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
    private String agency;
    private String address;
    @Column(unique = true)
    private String document;
    private String email;
    private String password;
//    private BigDecimal balance;
//    @Enumerated(EnumType.STRING)
//    private StatusType statusType;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private List<Account> accounts;

    public String getEmail() {return email;}

//    public StatusType getStatusType() {
//        return statusType;
//    }
//
//    public BigDecimal getBalance() {
//        return balance;
//    }
//
//    public void setBalance(BigDecimal balance) {
//        this.balance = balance;
//    }

    public User(UserDTO data){
        this.firstName = data.firstName();
        this.agency = data.agency();
//        this.account = data.account();
        this.address = data.address();
        this.document = data.document();
        this.email = data.email();
//        this.balance = data.balance();
//        this.statusType = data.statusType();
        this.password = data.password();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
