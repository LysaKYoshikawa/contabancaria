package com.yoshikawa.contabancaria.services;

import com.yoshikawa.contabancaria.DTOs.AccountDTO;
import com.yoshikawa.contabancaria.domain.account.Account;
import com.yoshikawa.contabancaria.domain.account.StatusType;
import com.yoshikawa.contabancaria.domain.user.User;
import com.yoshikawa.contabancaria.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public void validadeTransaction(Account sender, BigDecimal amount) throws Exception{
        if(sender.getStatusType() == StatusType.INACTIVE){
            throw  new Exception("Status da conta Inativa");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw  new Exception("Usuario não tem saldo");
        }

    }

    public Account findAccountById(Long id) throws Exception {
        return this.repository.findAccountById(id).orElseThrow(() -> new Exception("Conta não localizada"));
    }

    public Account createAccount(User user, Double agencia, BigDecimal balance, StatusType statusType, AccountDTO data) {
        Account newAccount = new Account(data);
        newAccount.setUser(user);
        newAccount.setAgencia(agencia);
        newAccount.setBalance(balance);
        newAccount.setStatusType(statusType);
        user.getAccounts().add(newAccount);
        saveAccount(user);
        return newAccount;
    }

    public List<Account> getAccountsByUserId(Long accountId) throws Exception {
        Account account = findAccountById(accountId);
        List<Account> accounts = account.getAccounts();
        return account.getAccounts();
    }

    public void saveAccount(Account account){
        this.repository.save(account);
    }
}
