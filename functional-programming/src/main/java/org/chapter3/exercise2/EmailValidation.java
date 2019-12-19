package org.chapter3.exercise2;

import org.chapter3.exercise1.Function;
import org.chapter3.exercise1.answer.Result;

import java.util.regex.Pattern;

public class EmailValidation {

    static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    static Function<String, Result<String>> emailChecker = s ->
            Case.match(
                    Case.mcase(()->Result.failure("email " + s + " is invalid.")),
                    Case.mcase(()->s==null,()->Result.failure("email must not be null")),
                    Case.mcase(()->s.length() == 0,()->Result.failure("email must not be empty")),
                    Case.mcase(()->emailPattern.matcher(s).matches(),()->Result.success(s))
                    );

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
