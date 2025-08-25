package com.smartjob.cl.bci.common.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PasswordValidator {

    @Value("${user.regex.password.regexp}")
    private String passwordRegex;

    public boolean isValidPassword(String password) {

        Pattern pattern = Pattern.compile(passwordRegex);

        if (password == null || password.isEmpty()) {
            return false;
        }

        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
