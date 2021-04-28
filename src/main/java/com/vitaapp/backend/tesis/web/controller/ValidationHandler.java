package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {


        ResponsePersonalized response = new ResponsePersonalized(400, "Error en la consulta");
        exception.getConstraintViolations().forEach(error -> {
            response.getErrors().add(error.getMessage());
        });
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({TransactionSystemException.class})
    protected ResponseEntity<Object> handlePersistenceException(final Exception ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        Throwable cause = ((TransactionSystemException) ex).getRootCause();
        List<String> errors = new ArrayList<String>();
        if (cause instanceof ConstraintViolationException) {

            ConstraintViolationException consEx= (ConstraintViolationException) cause;

            for (ConstraintViolation<?> violation : consEx.getConstraintViolations()) {
                errors.add(violation.getPropertyPath() + ": " + violation.getMessage());
            }


        }
        ResponsePersonalized response = new ResponsePersonalized(400, "Error en la consulta");
        response.setErrors(errors);

        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        ResponsePersonalized response = new ResponsePersonalized(400, exception.getMessage());
        if(exception.getMessage().contains("SQL")) {
            response.getErrors().add(exception.getCause().getCause().getMessage());
        } else {
            response.getErrors().add(exception.getMessage());
        }

        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }


}
