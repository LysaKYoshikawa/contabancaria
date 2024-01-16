package com.yoshikawa.contabancaria.controllers;

import com.yoshikawa.contabancaria.DTOs.AccountDTO;
import com.yoshikawa.contabancaria.DTOs.UserDTO;
import com.yoshikawa.contabancaria.domain.account.Account;
import com.yoshikawa.contabancaria.domain.user.User;
import com.yoshikawa.contabancaria.services.UserService;
import com.yoshikawa.contabancaria.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user){
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);

    }


    @GetMapping
    public ResponseEntity<List<User>> getListUsers(){
        List<User> users = this.userService.getListUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/{userId}/conta")
    public ResponseEntity<Account> createAccount(@PathVariable Long userId, @RequestBody AccountDTO accountDTO) {
        try {
            User user = userService.findUserById(userId);
            Account newAccount = accountService.createAccount(user, accountDTO.getAgencia(), accountDTO.getBalance(), accountDTO.getUserType());
            return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}/contas")
    public ResponseEntity<List<Account>> getUserAccounts(@PathVariable Long userId) {
        try {
            List<Account> accounts = userService.getAccountsByUserId(userId);
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
