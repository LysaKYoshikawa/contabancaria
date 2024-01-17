package com.yoshikawa.contabancaria.DTOs;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderAccount, Long receiverAccount) {
}
