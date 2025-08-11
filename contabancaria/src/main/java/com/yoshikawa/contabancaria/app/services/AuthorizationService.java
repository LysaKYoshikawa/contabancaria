package com.yoshikawa.contabancaria.app.services;

import com.yoshikawa.contabancaria.domain.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${authorization.url}")
    private String authorizationUrl;

    public boolean authorizeTransaction(Account sender, BigDecimal value){
        try {
            ResponseEntity<Map> checkResponse = restTemplate.getForEntity(
                    authorizationUrl, Map.class
            );

            if (checkResponse.getStatusCode() == HttpStatus.OK && checkResponse.getBody() != null) {
                Object messageSentObject = checkResponse.getBody().get("messageSent");
                if (messageSentObject instanceof Boolean) {
                    return (Boolean) messageSentObject;
                }
            }
            return false;
        } catch (Exception e) {
            // Log o erro e retorne false para indicar falha na autorização
            System.err.println("Erro ao autorizar transação: " + e.getMessage());
            return false;
        }
    }
}
