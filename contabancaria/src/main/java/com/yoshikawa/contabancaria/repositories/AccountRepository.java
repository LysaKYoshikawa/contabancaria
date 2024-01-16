package com.yoshikawa.contabancaria.repositories;

import com.yoshikawa.contabancaria.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findAccountById(Long id);
}
