package com.yoshikawa.contabancaria.repositories;

import com.yoshikawa.contabancaria.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
}
