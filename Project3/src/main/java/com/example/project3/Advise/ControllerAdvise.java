package com.example.project3.Advise;

import com.example.project3.Api.ApiException;
import com.example.project3.Api.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice

public class ControllerAdvise {



        @ExceptionHandler(value = ApiException.class)//the type of exception from the api exception class
        public ResponseEntity ApiException(ApiException apiException) {

            String message = apiException.getMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));

        }



        // Server Validation Exception
        @ExceptionHandler(value = MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
            String msg = e.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(msg));
        }

        // Server Validation Exception
        @ExceptionHandler(value = ConstraintViolationException.class)
        public ResponseEntity<ApiResponse> ConstraintViolationException(ConstraintViolationException e) {
            String msg =e.getMessage();
            return ResponseEntity.status(400).body(new ApiResponse(msg));
        }


        // SQL Constraint Ex:(Duplicate) Exception
        @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
        public ResponseEntity<ApiResponse> SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e){
            String msg=e.getMessage();
            return ResponseEntity.status(400).body(new ApiResponse(msg));
        }

        // wrong write SQL in @column Exception
        @ExceptionHandler(value = InvalidDataAccessResourceUsageException.class )
        public ResponseEntity<ApiResponse> InvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException e){
            String msg=e.getMessage();
            return ResponseEntity.status(200).body(new ApiResponse(msg));
        }

        // Database Constraint Exception
        @ExceptionHandler(value = DataIntegrityViolationException.class)
        public ResponseEntity<ApiResponse> DataIntegrityViolationException(DataIntegrityViolationException e){
            String msg=e.getMessage();
            return ResponseEntity.status(400).body(new ApiResponse(msg));
        }

        // Method not allowed Exception
        @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
        public ResponseEntity<ApiResponse> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
            String msg = e.getMessage();
            return ResponseEntity.status(400).body(new ApiResponse(msg));
        }

        // Json parse Exception
        @ExceptionHandler(value = HttpMessageNotReadableException.class)
        public ResponseEntity<ApiResponse> HttpMessageNotReadableException(HttpMessageNotReadableException e) {
            String msg = e.getMessage();
            return ResponseEntity.status(400).body(new ApiResponse(msg));
        }

        // TypesMisMatch Exception
        @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
        public ResponseEntity<ApiResponse> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
            String msg = e.getMessage();
            return ResponseEntity.status(400).body(new ApiResponse(msg));
        }







    }
