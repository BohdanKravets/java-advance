package com.javaadvance.validator;

import com.javaadvance.entity.Computer;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ComputerValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Computer.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Computer computer = (Computer) o;
        StringUtils.isAlpha(computer.getBrand());
        if (StringUtils.isNotBlank(computer.getBrand())) {

            if (!StringUtils.isAlpha(computer.getBrand())) {
                errors.rejectValue("brand", "computer.brand.letters", "Computer brand should contain only letters");
            }
        }
    }
}
