package com.yoshikawa.contabancaria.DTOs;

import com.yoshikawa.contabancaria.domain.account.StatusType;

import java.math.BigDecimal;

public record AccountDTO(Double agencia, BigDecimal balance, StatusType statusType) {

}
