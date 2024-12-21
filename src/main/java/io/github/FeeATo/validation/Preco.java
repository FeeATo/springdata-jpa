package io.github.FeeATo.validation;

import io.github.FeeATo.validation.constraintvalidation.NotZeroNumberValidator;
import io.github.FeeATo.validation.constraintvalidation.PrecoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = PrecoValidator.class)
public @interface Preco {

    String message() default ("O formato do preço deve ser: 0.00");

    //Precisa desses parâmetros por algum motivo. Pesquisa na internet caso você esqueça o motivo (não busquei por muitos detalhes)
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
