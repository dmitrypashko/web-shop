package com.gmail.dmitrypashko.dmitry.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.gmail.dmitrypashko.dmitry.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
        Pattern pattern1 = Pattern.compile("[a-zA-Z]{3,16}$");
        Matcher matcher1 = pattern1.matcher(user.getName());
        if (!matcher1.find()) {
            errors.rejectValue("name", "Correct.user.name");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "Required");
        Pattern pattern2 = Pattern.compile("[a-zA-Z]{3,16}$");
        Matcher matcher2 = pattern2.matcher(user.getSurname());
        if (!matcher2.find()) {
            errors.rejectValue("surname", "Correct.user.surname");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");
        Pattern pattern3 = Pattern.compile("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$");
        Matcher matcher3 = pattern3.matcher(user.getEmail());
        if (!matcher3.find()) {
            errors.rejectValue("email", "Correct.user.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "Required");
        Pattern pattern4 = Pattern.compile("[a-zA-Z]{3,16}$");
        Pattern pattern5 = Pattern.compile("([a-zA-Z]{1,3})?[0-9]{1,4}([a-zA-Z]{1,3})?$");
        Matcher matcher4 = pattern4.matcher(user.getAddress().getCountry());
        Matcher matcher5 = pattern4.matcher(user.getAddress().getCity());
        Matcher matcher6 = pattern4.matcher(user.getAddress().getStreet());
        Matcher matcher7 = pattern5.matcher(user.getAddress().getBuilding());
        Matcher matcher8 = pattern5.matcher(user.getAddress().getApartmentNumber());
        if (!matcher4.find() || !matcher5.find() || !matcher6.find() || !matcher7.find() || !matcher8.find()) {
            errors.rejectValue("address", "Correct.user.address");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "Required");
        Pattern pattern6 = Pattern.compile("^((8|\\+7|\\+3)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
        Matcher matcher9 = pattern6.matcher(user.getPhoneNumber());
        if (!matcher9.find()) {
            errors.rejectValue("phoneNumber", "Correct.user.phoneNumber");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("password", "Match.user.password");
        } else {
            Pattern pattern7 = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$");
            Matcher matcher10 = pattern7.matcher(user.getPassword());
            if (!matcher10.find()) {
                errors.rejectValue("password", "Correct.user.password");
            }
        }
    }

}