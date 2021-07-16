package com.javaadvance.validator;

import com.javaadvance.entity.Apartment;
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
        if (StringUtils.isNotBlank(apartment.getAddress())) {
            if (!StringUtils.isAlpha(apartment.getAddress())) {
                errors.rejectValue("address", "apartment.address.only-letters",
                        "Address should contain only letters");
            }
        }
    }
}
