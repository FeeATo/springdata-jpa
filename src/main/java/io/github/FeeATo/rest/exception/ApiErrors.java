package io.github.FeeATo.rest.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class ApiErrors {

    List<String> errors;

    public ApiErrors(String error) {
        this.errors = Arrays.asList(error);
    }

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }
}
