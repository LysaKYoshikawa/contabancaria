package com.yoshikawa.contabancaria.services;

import com.yoshikawa.contabancaria.DTOs.UserDTO;
import com.yoshikawa.contabancaria.domain.account.Account;
import com.yoshikawa.contabancaria.domain.user.User;
import com.yoshikawa.contabancaria.domain.user.StatusType;
import com.yoshikawa.contabancaria.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


    public User findUserByAgency(String agency) throws Exception {
        return this.repository.findUserByAgency(agency).orElseThrow(() -> new Exception("Conta não localizada"));
    }

    public User findUserByDocument(String document) throws Exception {
        return this.repository.findUserByDocument(document).orElseThrow(() -> new Exception("Usuário não localizado"));
    }
    @Transactional
    public User createUser(UserDTO data){
        User newUser = new User(data);

        Account account = new Account();
        account.setBalance(data.balance());
        account.setAgency(data.agency());
        account.setStatusType(data.statusType());
        account.setDocument(data.document());

        newUser.setAccounts(List.of(account));

        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getListUsers(){
        return this.repository.findAll();
    }

    public void saveUser(User user){
        this.repository.save(user);
    }
}
