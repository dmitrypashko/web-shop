package com.gmail.dmitrypashko.dmitry.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.gmail.dmitrypashko.dmitry.model.Product;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
       Product product = (Product) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameProduct", "Required");
        Pattern pattern2 = Pattern.compile("[a-zA-Z]{2,30}$");
        Matcher matcher2 = pattern2.matcher(product.getNameProduct());
        if (!matcher2.find()) {
            errors.rejectValue("nameProduct", "Correct.product.name");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "Required");
        Pattern pattern3 = Pattern.compile("[1-9]{1,5}$");
        if (product.getPrice() != null){
            Matcher matcher3 = pattern3.matcher(product.getPrice().toString());
            if (!matcher3.find()) {
                errors.rejectValue("price", "Correct.product.price");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Required");
        Pattern pattern4 = Pattern.compile("[a-zA-Z]{10,500}$");
        Matcher matcher4 = pattern4.matcher(product.getDescription());
        if (!matcher4.find()) {
            errors.rejectValue("description", "Correct.product.description");
        }
    }
}
