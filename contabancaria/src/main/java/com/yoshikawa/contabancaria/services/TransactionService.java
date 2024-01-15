package com.yoshikawa.contabancaria.services;

import com.yoshikawa.contabancaria.DTOs.TransactionDTO;
import com.yoshikawa.contabancaria.domain.user.User;
import com.yoshikawa.contabancaria.repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private  UserService userService;

    @Autowired
    private TransactionsRepository repository;

    public void createTransaction(TransactionDTO transaction) throws Exception{
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

    }
}
