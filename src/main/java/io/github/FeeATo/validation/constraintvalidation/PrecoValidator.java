package io.github.FeeATo.validation.constraintvalidation;

import io.github.FeeATo.validation.Preco;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrecoValidator implements ConstraintValidator<Preco, Number> {

    private Number numero;
    @Override
    public void initialize(Preco constraintAnnotation) {
        constraintAnnotation.groups();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        String[] numero = number.toString().split("\\.");
        return numero.length == 2 && numero[1].length() <= 2;
    }
}
