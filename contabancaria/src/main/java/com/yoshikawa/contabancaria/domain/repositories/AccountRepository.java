package com.yoshikawa.contabancaria.domain.repositories;

import com.yoshikawa.contabancaria.domain.account.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
