package com.bugfi.productservice.exceptionHandlers;

import com.bugfi.productservice.dtos.ExceptionDto;
import com.bugfi.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setError(e.getMessage());
        exceptionDto.setMessage("Invalid product id: " + e.getId());
        return ResponseEntity.status(404).body(exceptionDto);
    }

    @ExceptionHandler(Exception.class)
        public ResponseEntity<ExceptionDto> handleException(Exception e) {
            ExceptionDto exceptionDto = new ExceptionDto();
            exceptionDto.setError("Internal Server Error");
            exceptionDto.setMessage(e.getMessage());
            return ResponseEntity.status(500).body(exceptionDto);
    }
}