package com.cabapp.pro.controller;

import com.cabapp.pro.dto.ExceptionResponseDto;
import com.cabapp.pro.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerException extends Exception{
        @ExceptionHandler(CustomerException.class)
        public ResponseEntity<Object> CabServiceException(CustomerException ex) {
            ExceptionResponseDto message = new ExceptionResponseDto(
                    ex.getStatusCode(),ex.getMessage());
            return ResponseEntity.status(ex.getStatusCode()).body(message);
        }

    @ExceptionHandler(CabException.class)
    public ResponseEntity<Object> CabServiceException(CabException ex) {
        ExceptionResponseDto message = new ExceptionResponseDto(
                ex.getStatusCode(),ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(message);
    }

    @ExceptionHandler(DriverException.class)
    public ResponseEntity<Object> CabServiceException(DriverException ex) {
        ExceptionResponseDto message = new ExceptionResponseDto(
                ex.getStatusCode(),ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(message);
    }

    @ExceptionHandler(AdminException.class)
    public ResponseEntity<Object> CabServiceException(AdminException ex) {
        ExceptionResponseDto message = new ExceptionResponseDto(
                ex.getStatusCode(),ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(message);
    }

    @ExceptionHandler(ComplaintException.class)
    public ResponseEntity<Object> CabServiceException(ComplaintException ex) {
        ExceptionResponseDto message = new ExceptionResponseDto(
                ex.getStatusCode(),ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(message);
    }

    }
