package com.yoshikawa.contabancaria.services;

import com.yoshikawa.contabancaria.domain.user.User;
import com.yoshikawa.contabancaria.domain.user.UserType;
import com.yoshikawa.contabancaria.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validadeTransaction(User sender, BigDecimal amount) throws Exception{
        if(sender.getUserType() == UserType.MERCHANT){
            throw  new Exception("Usuario de de conta Inativa");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw  new Exception("Usuario não tem saldo");
        }

    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuario não cadastrado"));
    }

    public void saveUser(User user){
        this.repository.save(user);
    }
}
