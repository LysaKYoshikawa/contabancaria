package com.yoshikawa.contabancaria.app.DTOs;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderAccount, Long receiverAccount) {
}
