package com.yoshikawa.contabancaria.exception;

import com.yoshikawa.contabancaria.DTOs.ExceptionDTO;
import com.yoshikawa.contabancaria.exception.AccountNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.proxy.EntityNotFoundDelegate;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Ocorreu um erro na transacao", "400");
        return ResponseEntity.badRequest().body(exceptionDTO);

    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralException(Exception exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(),"500");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity threatAccountNotFoundException(AccountNotFoundException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(),"422");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }
}
