package com.yoshikawa.contabancaria.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class UserRequestEntity {
    private String firstName;
    private String agency;
    private String account;
    private String address;
    private String document;
    private String email;
    private String password;

    public UserRequestEntity(String firstName, String agency, String account, String address, String document, String email, String password) {
        this.firstName = Objects.requireNonNull(firstName, "First name cannot be null");
        this.agency = Objects.requireNonNull(agency, "Agency cannot be null");
        this.account = Objects.requireNonNull(account, "Account cannot be null");
        this.address = Objects.requireNonNull(address, "Address cannot be null");
        this.document = Objects.requireNonNull(document, "Document cannot be null");
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.password = Objects.requireNonNull(password, "Password cannot be null");

        if(account.length() < 4) {
            throw new IllegalArgumentException("Account must be at least 5 characters long");
        }
    }

}
