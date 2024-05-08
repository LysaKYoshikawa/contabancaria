package com.yoshikawa.contabancaria.services;

import com.yoshikawa.contabancaria.domain.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;



@Service
public class AuthorizationService {

    @Autowired
    private RestTemplate restTemplate;

    public boolean authorizeTransaction(Account sender, BigDecimal value){
        ResponseEntity<Map> checkResponse= restTemplate.getForEntity("https://run.mocky.io/v3/9769bf3a-b0b6-477a-9ff5-91f63010c9d3", Map.class);

        Object messageSentObject = checkResponse.getBody().get("messageSent");

        if(checkResponse.getStatusCode() == HttpStatus.OK && messageSentObject != null){
            return Boolean.TRUE.equals(messageSentObject);
        }else return false;

    }
}
