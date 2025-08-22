package com.yoshikawa.contabancaria.infra.dataProvider.impl;

import org.springframework.http.ResponseEntity;

public class UserCreateDataProviderImpl {

    public ResponseEntity<String> createUser(String userData) {
        // Logic to create a user in the data provider
        // component para chamar o usecase onde tera a logica de criar o usuario
        return ResponseEntity.ok("User created successfully in data provider");
    }
}
