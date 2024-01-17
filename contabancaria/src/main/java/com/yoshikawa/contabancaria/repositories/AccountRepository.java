package com.yoshikawa.contabancaria.repositories;

import com.yoshikawa.contabancaria.domain.account.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
