package com.yoshikawa.contabancaria.DTOs;

public record UserDTO(String firstName,

                      String address,
                      String document,

                      String email,
                      String password
                      ) {
}
