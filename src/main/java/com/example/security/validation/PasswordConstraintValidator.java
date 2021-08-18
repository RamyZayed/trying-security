package com.example.security.validation;

import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword,String> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(new LengthRule(8, 30)));
        RuleResult result = validator.validate(new PasswordData(password));

        if (result.isValid()){
            return true;
        }
        context.buildConstraintViolationWithTemplate(validator.getMessages(result).toString()).addConstraintViolation();
        return false;
    }
}
