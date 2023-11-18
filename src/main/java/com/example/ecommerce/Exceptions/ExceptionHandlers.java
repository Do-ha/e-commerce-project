package com.example.ecommerce.Exceptions;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

  @ExceptionHandler({DataIntegrityViolationException.class})
  public ResponseEntity<?> handelDataIntegrityViolationException(DataIntegrityViolationException e){
    return ResponseEntity.status(HttpStatus.CONFLICT).body("already exists");
  }
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<?> handleNotFoundException(EntityNotFoundException e) {
//    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

}
