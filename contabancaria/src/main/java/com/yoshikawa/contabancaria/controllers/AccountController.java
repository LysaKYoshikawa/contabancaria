package com.yoshikawa.contabancaria.controllers;

import com.yoshikawa.contabancaria.DTOs.AccountDTO;
import com.yoshikawa.contabancaria.DTOs.UserDTO;
import com.yoshikawa.contabancaria.domain.account.Account;
import com.yoshikawa.contabancaria.domain.user.User;
import com.yoshikawa.contabancaria.services.AccountService;
import com.yoshikawa.contabancaria.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
