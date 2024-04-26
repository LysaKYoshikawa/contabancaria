package com.yoshikawa.contabancaria.DTOs;

import com.yoshikawa.contabancaria.domain.user.StatusType;
import org.junit.jupiter.api.Test;
import pl.pojo.tester.api.assertion.Method;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public record dtoTest() {

    @Test
    public void testAccountDTO() {
        // Criação de um objeto AccountDTO para teste
        String document = "123456789";
        String agency = "001";
        BigDecimal balance = new BigDecimal("1000.00");
        StatusType statusType = StatusType.ACTIVE;

        AccountDTO accountDTO = new AccountDTO(document, agency, balance, statusType);

        // Verificação dos valores dos atributos
        assertEquals(document, accountDTO.getDocument());
        assertEquals(agency, accountDTO.getAgency());
        assertEquals(balance, accountDTO.getBalance());
        assertEquals(statusType, accountDTO.getStatusType());
    }

}