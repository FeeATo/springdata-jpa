package io.github.FeeATo.validation.constraintvalidation;

import io.github.FeeATo.validation.NotZeroNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotZeroNumberValidator implements ConstraintValidator<NotZeroNumber, Number> {


    @Override
    public void initialize(NotZeroNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Number num, ConstraintValidatorContext constraintValidatorContext) {
        return num != null && !num.toString().replace(".", "").replace("0", "").isEmpty();
    }
}
