package io.github.FeeATo.rest.controller;

import io.github.FeeATo.rest.exception.ApiErrors;
import io.github.FeeATo.rest.exception.VendasException;
import io.github.FeeATo.rest.exception.VendasRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(VendasRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleVendaRuntimeException(VendasRuntimeException ex) {
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(VendasException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrors handleVendaException(VendasException ex) {
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex) {
        return new ApiErrors(ex.getBindingResult().getAllErrors()
                .stream()
                .map(e->e.getDefaultMessage())
                .collect(Collectors.toList()));

    }
}
