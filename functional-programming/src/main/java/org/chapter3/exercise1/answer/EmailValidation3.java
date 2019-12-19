package org.chapter3.exercise1.answer;

import org.chapter3.exercise1.Executable;
import org.chapter3.exercise1.Function;

import java.util.regex.Pattern;

public class EmailValidation3 {

    static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    static Function<String, Result<String>> emailChecker = s ->
            s == null
                    ? Result.failure("email must not be null")
                    : s.length() == 0
                    ? Result.failure("email must not be empty")
                    : emailPattern.matcher(s).matches()
                    ? Result.success(s)
                    : Result.failure("email " + s + " is invalid.");

    public static void main(String... args) {
        validate("this.is@my.email");
        validate(null);
        validate("");
        validate("john.doe@acme.com");
    }

    private static void logError(String s) {
        System.err.println("Error message logged: " + s);
    }

    private static void sendVerificationMail(String s) {
        System.out.println("Mail sent to " + s);
    }

    static void validate(String s) {
        Result<String> result = emailChecker.apply(s);
        result.bind(s1 -> sendVerificationMail(s), errorMessage -> logError(errorMessage));
    }

}
