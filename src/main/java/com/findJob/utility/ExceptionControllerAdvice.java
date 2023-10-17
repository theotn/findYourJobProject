package com.findJob.utility;

import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> exceptionHandler(MethodArgumentNotValidException exception) {

        ErrorInfo error = new ErrorInfo();
        error.setErrorMessage(exception.getMessage());

        String errorMsg = exception.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage())
                .collect(Collectors.joining(", "));

        error.setErrorMessage(errorMsg);
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setTimeStamp(LocalDateTime.now());


        return new ResponseEntity<ErrorInfo>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorInfo> eventExceptionHandler(BadRequestException exception) {

        ErrorInfo error = new ErrorInfo();

        error.setErrorMessage(exception.getMessage());
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<ErrorInfo>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorInfo> eventExceptionHandler(NotFoundException exception) {

        ErrorInfo error = new ErrorInfo();

        error.setErrorMessage(exception.getMessage());
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<ErrorInfo>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorInfo> constraintViolationHandler(ConstraintViolationException exception) {

        ErrorInfo errorInfo = new ErrorInfo();
        String message = exception.getConstraintViolations()
                .stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));

        errorInfo.setErrorMessage(message);
        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorInfo.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception) {

        ErrorInfo errorInfo = new ErrorInfo();
        String message = exception.getMessage();
        errorInfo.setErrorMessage(message);
        errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorInfo.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}


