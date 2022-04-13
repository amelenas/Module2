package com.epam.esm.controller.exception;

import com.epam.esm.service.exception.ServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@EnableWebMvc
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler   {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> handleServiceException(ServiceException ex) {
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST.toString(), "Something went wrong.", HttpStatus.BAD_REQUEST, "4041");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST.toString(), "Something went wrong.", HttpStatus.BAD_REQUEST, "4042");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity <Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST.toString(), "Something went wrong.", HttpStatus.BAD_REQUEST, "4043");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

        @ExceptionHandler({MethodArgumentTypeMismatchException.class, JsonProcessingException.class})
    public final ResponseEntity<Object> handleBadRequestExceptions() {
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST.toString(), "Something went wrong.", HttpStatus.BAD_REQUEST, "4044");
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("Malformed JSON Request", ex.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        ErrorResponse errorResponse;
        errorResponse = new ErrorResponse("Method Argument Not Valid", errors, HttpStatus.BAD_REQUEST);
        if (errors.size() == 0) {
            errorResponse = new ErrorResponse("Method Argument Not Valid", HttpStatus.BAD_REQUEST.toString());
        }
        return new ResponseEntity<>(errorResponse, status);
    }
}