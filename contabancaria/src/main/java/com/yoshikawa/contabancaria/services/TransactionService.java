package com.yoshikawa.contabancaria.services;

import com.yoshikawa.contabancaria.DTOs.TransactionDTO;
import com.yoshikawa.contabancaria.domain.transaction.Transaction;
import com.yoshikawa.contabancaria.domain.user.User;
import com.yoshikawa.contabancaria.repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private  UserService userService;

    @Autowired
    private TransactionsRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public void createTransaction(TransactionDTO transaction) throws Exception{
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validadeTransaction(sender, transaction.value());

        boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());
        if(!isAuthorized){
            throw new Exception("Transferência não autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

    }

    public boolean authorizeTransaction(User sender, BigDecimal value){
        ResponseEntity<Map> checkResponse= restTemplate.getForEntity("https://run.mocky.io/v3/9769bf3a-b0b6-477a-9ff5-91f63010c9d3", Map.class);

        Object messageSentObject = checkResponse.getBody().get("messageSent");

        if(checkResponse.getStatusCode() == HttpStatus.OK && messageSentObject != null){
            return Boolean.TRUE.equals(messageSentObject);
        }else return false;

    }
}
