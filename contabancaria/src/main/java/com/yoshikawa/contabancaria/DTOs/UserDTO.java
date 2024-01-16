package com.yoshikawa.contabancaria.DTOs;

import com.yoshikawa.contabancaria.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName,
                      String lastName,
                      String address,
                      String document,
                      BigDecimal balance,
                      String email,
                      String password,
                      UserType userType) {
}
