package com.yoshikawa.contabancaria.app.controllers;

import com.yoshikawa.contabancaria.app.DTOs.AccountDTO;
import com.yoshikawa.contabancaria.domain.account.Account;
import com.yoshikawa.contabancaria.app.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO accountDTO) throws Exception {
        Account newAccount = accountService.createAccount(accountDTO);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);

    }

}
