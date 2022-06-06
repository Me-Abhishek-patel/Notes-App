package com.ciberciti.notes.utils;

import java.util.regex.Pattern;

public class InputValidator {
    public boolean validateIndianMobileNumber(String number) {
        if (number == null) return false;
        Pattern pattern = Pattern.compile("^[6-9]\\d{9}$");
        return pattern.matcher(number).matches();
    }

    public boolean validateEmailAddress(String email) {
        if (email == null) return false;
        Pattern pattern = Pattern.compile("[a-z0-9]{4,25}@[a-z]{4,25}\\.[a-z]{2,6}");
        return pattern.matcher(email).matches();
    }

    public boolean validatePassword(String password, String name) {
        if (password.length() < 8) return false;
        if (password.length() > 15) return false;
        if (password.toLowerCase().contains(name.toLowerCase())) return false;
        if (Character.isUpperCase(password.charAt(0))) return false;
        int upperCaseCount = 0, digitCount = 0, specialCharCount = 0;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) upperCaseCount++;
            if (Character.isDigit(c)) digitCount++;
            if (Character.isLetterOrDigit(c)) specialCharCount++;
        }
        if (upperCaseCount < 2) return false;
        if (digitCount < 2) return false;
        return specialCharCount >= 1;

    }
}
