package com.yoshikawa.contabancaria.repositories;

import com.yoshikawa.contabancaria.DTOs.UserDTO;
import com.yoshikawa.contabancaria.domain.user.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Test
    void findUserByDocument() {
    }

    private User createUser(UserDTO data){

    }
}