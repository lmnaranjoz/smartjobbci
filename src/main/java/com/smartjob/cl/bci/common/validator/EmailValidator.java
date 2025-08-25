package com.smartjob.cl.bci.common.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailValidator {

    @Value("${user.regex.email.regexp}")
    private String emailRegex;

    public boolean isValidEmail(String email) {

        Pattern pattern = Pattern.compile(emailRegex);

        if (email == null || email.isEmpty()) {
            return false;
        }

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
