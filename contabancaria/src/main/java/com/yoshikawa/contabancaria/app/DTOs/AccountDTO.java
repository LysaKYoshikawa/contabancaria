package com.yoshikawa.contabancaria.app.DTOs;

import com.yoshikawa.contabancaria.domain.user.StatusType;

import java.math.BigDecimal;

public record AccountDTO(String document,
                         String agency,
                         BigDecimal balance,
                         StatusType statusType) {
}
