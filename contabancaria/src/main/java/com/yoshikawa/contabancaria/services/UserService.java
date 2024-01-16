package com.yoshikawa.contabancaria.services;

import com.yoshikawa.contabancaria.DTOs.AccountDTO;
import com.yoshikawa.contabancaria.DTOs.UserDTO;
import com.yoshikawa.contabancaria.domain.account.Account;
import com.yoshikawa.contabancaria.domain.user.User;
import com.yoshikawa.contabancaria.domain.account.StatusType;
import com.yoshikawa.contabancaria.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validadeTransaction(Account sender, BigDecimal amount) throws Exception{
        if(sender.getStatusType() == StatusType.INACTIVE){
            throw  new Exception("Status da conta Inativa");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw  new Exception("Usuario não tem saldo");
        }

    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuario não cadastrado"));
    }

    public User createUser(UserDTO data){
        User newUser = new User(data);
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
