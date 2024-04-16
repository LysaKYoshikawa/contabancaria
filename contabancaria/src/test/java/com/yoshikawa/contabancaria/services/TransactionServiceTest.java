package com.yoshikawa.contabancaria.services;

import com.yoshikawa.contabancaria.DTOs.TransactionDTO;
import com.yoshikawa.contabancaria.domain.account.Account;
import com.yoshikawa.contabancaria.domain.user.StatusType;
import com.yoshikawa.contabancaria.repositories.TransactionsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private  UserService userService;

    @Mock
    private  AccountService accountService;

    @Mock
    private TransactionsRepository repository;

    @Mock
    private AuthorizationService authService;

    @Mock
    private NotificationService notificationService;

    @Autowired
    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Deve criar a transação com sucesso quando tudo estiver OK")
    void createTransactionCase1() throws Exception {
        Account sender = new Account(1L,"0102",new BigDecimal(100),"123456", StatusType.COMMON);
        Account receiver = new Account(2L,"0123",new BigDecimal(50),"654321", StatusType.COMMON);

        when(accountService.findAccount(1L)).thenReturn(sender);
        when(accountService.findAccount(2L)).thenReturn(receiver);

        when(authService.authorizeTransaction(any(),any())).thenReturn(true);

        TransactionDTO request = new TransactionDTO(new BigDecimal(10),1L,2L);
        transactionService.createTransaction(request);

        verify(repository, times(1)).save(any());

        sender.setBalance(new BigDecimal(0));
        verify(accountService, times(1)).saveAccount(sender);

        receiver.setBalance(new BigDecimal(150));
        verify(accountService, times(1)).saveAccount(receiver);

        verify(notificationService, times(1)).sendNotification(userService.findUserByAgency("0102"), "Pagamento realizado com sucesso");
        verify(notificationService, times(1)).sendNotification(userService.findUserByAgency("0123"), "Pagamento recebido com sucesso");
    }

    @Test
    @DisplayName("Deve criar a Exception quando a trasação estiver permitida")
    void createTransactionCase2() {

        Account sender = new Account(1L,"0102",new BigDecimal(100),"123456", StatusType.COMMON);
        Account receiver = new Account(2L,"0123",new BigDecimal(50),"654321", StatusType.COMMON);

        when(accountService.findAccount(1L)).thenReturn(sender);
        when(accountService.findAccount(2L)).thenReturn(receiver);

        when(authService.authorizeTransaction(any(),any())).thenReturn(false);

        Exception thrown = Assertions.assertThrows(Exception.class,() ->{
            TransactionDTO request = new TransactionDTO(new BigDecimal(10),1L,2L);
            transactionService.createTransaction(request);
        });

        Assertions.assertEquals("Transferência não autorizada", thrown.getMessage());


    }
}