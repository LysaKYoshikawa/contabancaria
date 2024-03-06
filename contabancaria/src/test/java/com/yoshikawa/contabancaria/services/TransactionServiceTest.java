package com.yoshikawa.contabancaria.services;

import com.yoshikawa.contabancaria.domain.account.Account;
import com.yoshikawa.contabancaria.repositories.TransactionsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    @Mock
    private  UserService userService;

    @Mock
    private  AccountService accountService;

    @Mock
    private TransactionsRepository repository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private NotificationService notificationService;

    @Autowired
    @InjectMocks
    private TransactionService sransactionService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Deve criar a transação com sucesso quando tudo estiver OK")
    void createTransactionSucess() {
        Account sender = new Account();
        Account receiver = new Account();
    }

    @Test
    @DisplayName("Deve criar a Exception quando a trasação estiver permitida")
    void createTransactionEmpty() {
    }
}