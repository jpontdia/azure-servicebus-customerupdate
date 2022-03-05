package com.demo.commons.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyStringValidator implements ConstraintValidator<NotEmptyString, String> {

    @Override
    public void initialize(NotEmptyString stringField) {
        //This method doesn't need to be implemented
    }

    @Override
    public boolean isValid(final String stringField, final ConstraintValidatorContext context) {
        return stringField != null && !"".equals(stringField.trim());
    }
}
