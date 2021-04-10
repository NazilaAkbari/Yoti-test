package com.yoti.hoovering.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CoordinationValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateCoordination{
    String message() default "Coordination Can not be negative";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
