package com.dojo.dojo_pitest.controller;

import com.dojo.dojo_pitest.exception.AppError;
import com.dojo.dojo_pitest.exception.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<AppError> applicationRuntimeError(ApplicationException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new AppError(exception.getMessage()));
    }
}
