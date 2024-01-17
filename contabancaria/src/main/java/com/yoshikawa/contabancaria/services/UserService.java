package com.yoshikawa.contabancaria.services;

import com.yoshikawa.contabancaria.DTOs.UserDTO;
import com.yoshikawa.contabancaria.domain.user.User;
import com.yoshikawa.contabancaria.domain.user.StatusType;
import com.yoshikawa.contabancaria.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validadeTransaction(User sender, BigDecimal amount) throws Exception{
        if(sender.getStatusType() == StatusType.INACTIVE){
            throw  new Exception("Não autorizado fazer transferencia conta Inativa.");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw  new Exception("Conta não tem saldo o suficiente para essa transação!");
        }

    }

    public User findUserByAgency(String agency ) throws Exception {
        return this.repository.findUserByAgency(agency).orElseThrow(() -> new Exception("Conta não localizada"));
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
