package com.yoshikawa.contabancaria.DTOs;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, String senderAgency, String receiverAgency) {
}
