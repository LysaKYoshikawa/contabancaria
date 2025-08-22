package com.yoshikawa.contabancaria.infra.dataProvider;

import org.springframework.http.ResponseEntity;

public interface UserCreateDataProvider {

    ResponseEntity<String> createUser(String userData);
}
