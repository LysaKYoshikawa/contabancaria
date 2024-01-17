package com.yoshikawa.contabancaria.repositories;

import com.yoshikawa.contabancaria.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByDocument(String document);
    Optional<User> findUserByAgency(String agency);
}
