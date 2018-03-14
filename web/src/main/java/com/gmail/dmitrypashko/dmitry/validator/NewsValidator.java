package com.gmail.dmitrypashko.dmitry.validator;

import com.gmail.dmitrypashko.dmitry.model.News;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class NewsValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return News.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
       News news = (News) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "header", "Required");
        Pattern pattern1 = Pattern.compile("[a-zA-Z]{2,30}$");
        Matcher matcher1 = pattern1.matcher(news.getHeader());
        if (!matcher1.find()) {
            errors.rejectValue("header", "Correct.news.title");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "Required");
        Pattern pattern2 = Pattern.compile("[a-zA-Z,1-9]{100,2000}$");
        Matcher matcher2 = pattern2.matcher(news.getContent());
        if (!matcher2.find()) {
            errors.rejectValue("content", "Correct.news.description");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "Required");
        Pattern pattern3 = Pattern.compile("((19|20)\\d\\d)-(0?[1-9]|1[1-2])-(0?[1-9]|[1][0-9]|3[0-1])");
        Matcher matcher3 = pattern3.matcher(news.getDate());
        if (!matcher3.find()) {
            errors.rejectValue("date", "Correct.news.date");
        }
    }
}
