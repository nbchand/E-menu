package com.ncit.emenu.feature;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {

    public static boolean checkEmailPattern(String email){
        final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }

    public static boolean checkNamePattern(String name){
        final String NAME_REGEX = "^[A-Za-z]{1,20}$";
        //1-20 characters, only containing alphabets
		final Pattern PATTERN = Pattern.compile(NAME_REGEX);
		Matcher matcher = PATTERN.matcher(name);
        return matcher.matches();
    }

    public static boolean checkPasswordPattern(String password){
        final String PASSWORD_REGEX = "^(?=\\S+$).{8,30}";
        //validates passwords which don't have white_spaces and are 8-30 characters long
		final Pattern PATTERN = Pattern.compile(PASSWORD_REGEX);
		Matcher matcher = PATTERN.matcher(password);
        return matcher.matches();
    }

}
