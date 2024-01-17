package com.yoshikawa.contabancaria.services;

import com.yoshikawa.contabancaria.DTOs.AccountDTO;
import com.yoshikawa.contabancaria.domain.account.Account;
import com.yoshikawa.contabancaria.domain.user.User;
import com.yoshikawa.contabancaria.exception.AccountNotFoundException;
import com.yoshikawa.contabancaria.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public Account createAccount(AccountDTO data) throws Exception {

        User user = userService.findUserByDocument(data.document());

        Account account = new Account();
        account.setAgency(data.agency());
        account.setBalance(data.balance());
        account.setStatusType(data.statusType());
        account.setDocument(data.document());

        Account accountSaved = accountRepository.save(account);

        List<Account> accountListUser = user.getAccounts();
        accountListUser.add(account);

        userService.saveUser(user);

        return accountSaved;
    }

    public Account findAccount(Long accountNumber) {
        Optional<Account> optionalAccount = accountRepository.findById(accountNumber);
        return optionalAccount.orElseThrow(() -> new AccountNotFoundException("Conta não localizada"));
    }

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }
}
