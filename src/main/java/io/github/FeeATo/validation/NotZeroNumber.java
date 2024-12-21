package io.github.FeeATo.validation;

import io.github.FeeATo.validation.constraintvalidation.NotEmptyListValidator;
import io.github.FeeATo.validation.constraintvalidation.NotZeroNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotZeroNumberValidator.class)
public @interface NotZeroNumber {
    String message() default ("Valor não pode ser nulo ou 0.");

    //Precisa desses parâmetros por algum motivo. Pesquisa na internet caso você esqueça o motivo (não busquei por muitos detalhes)
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
