package com.yoshikawa.contabancaria.domain.user;


import com.yoshikawa.contabancaria.DTOs.UserDTO;
import com.yoshikawa.contabancaria.domain.account.Account;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
    private String address;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();


    public String getEmail() {return email;}



    public User(UserDTO data){
        this.firstName = data.firstName();

        this.address = data.address();
        this.document = data.document();
        this.email = data.email();
        this.password = data.password();
    }



}
