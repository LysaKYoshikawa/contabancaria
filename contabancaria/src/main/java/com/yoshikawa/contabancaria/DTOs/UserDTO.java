package com.yoshikawa.contabancaria.DTOs;

import com.yoshikawa.contabancaria.domain.user.StatusType;

import java.math.BigDecimal;

public record UserDTO(String firstName,
                      String agencia,
                      String address,
                      String document,
                      BigDecimal balance,
                      String email,
                      String password,
                      StatusType statusType) {
}
