package com.yoshikawa.contabancaria.domain.repositories;

import com.yoshikawa.contabancaria.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByDocument(String document);
    Optional<User> findUserByAgency(String agency);
}
