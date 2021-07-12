package com.javaadvance.validator;

import com.javaadvance.entity.Apartment;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ApartmentValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Apartment.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
Apartment apartment = (Apartment) o;
if( StringUtils.isNotBlank(apartment.getAddress()) &&
        !CharUtils.isAsciiAlphaUpper(apartment.getAddress().charAt(0))) {
    errors.rejectValue("address","apartment.address.capital-letter",
            "Movie title should start with capital letter");
}
    }
}
