package com.demo.commons.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = NotEmptyStringValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented

public @interface NotEmptyString {
    String message() default "The field must not be empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
