package com.yoshikawa.contabancaria.services;

import com.yoshikawa.contabancaria.DTOs.TransactionDTO;
import com.yoshikawa.contabancaria.domain.account.Account;
import com.yoshikawa.contabancaria.domain.transaction.Transaction;
import com.yoshikawa.contabancaria.domain.user.StatusType;
import com.yoshikawa.contabancaria.domain.user.User;
import com.yoshikawa.contabancaria.repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
public class TransactionService {

    @Autowired
    private  UserService userService;

    @Autowired
    private  AccountService accountService;

    @Autowired
    private TransactionsRepository repository;

    @Autowired
    private AuthorizationService authService;

    @Autowired
    private NotificationService notificationService;
    @Transactional
    public Transaction createTransaction(TransactionDTO transaction) throws Exception{

        Account senderAccount = this.accountService.findAccount(transaction.senderAccount());

        Account receiverAccount = this.accountService.findAccount(transaction.receiverAccount());

        validadeTransaction(senderAccount, receiverAccount, transaction.value());

        boolean isAuthorized = this. authService.authorizeTransaction(senderAccount, transaction.value());
        if(!isAuthorized){
            throw new Exception("Transferência não autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(senderAccount);
        newTransaction.setReceiver(receiverAccount);
        newTransaction.setTimestamp(LocalDateTime.now());

        senderAccount.setBalance(senderAccount.getBalance().subtract(transaction.value()));
        receiverAccount.setBalance(receiverAccount.getBalance().add(transaction.value()));

        this.repository.save(newTransaction);
        this.accountService.saveAccount(senderAccount);
        this.accountService.saveAccount(receiverAccount);

        User senderUser = userService.findUserByDocument(senderAccount.getDocument());
        User receiverUser = userService.findUserByDocument(receiverAccount.getDocument());

        this.notificationService.sendNotification(senderUser, "Pagamento realizado com sucesso");
        this.notificationService.sendNotification(receiverUser, "Pagamento recebida com sucesso");

        return newTransaction;

    }



    public void validadeTransaction(Account sender, Account receiver, BigDecimal amount) throws Exception{
        if(sender.getStatusType() == StatusType.INACTIVE){
            throw  new Exception("Não autorizado fazer transferencia conta Inativa.");
        }

        if(receiver.getStatusType() == StatusType.INACTIVE){
            throw  new Exception("Não autorizado fazer transferencia conta Inativa.");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw  new Exception("Conta não tem saldo o suficiente para essa transação!");
        }

    }
}
