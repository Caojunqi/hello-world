package org.chapter3.exercise1.test1;

import org.chapter3.exercise1.Function;

import java.util.regex.Pattern;

public class EmailValidation2 {
    static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    static Function<String, Result> emailChecker = s ->
            s == null
                    ? new Result.Failure("email must not be null")
                    : s.length() == 0
                    ? new Result.Failure("email must not be empty")
                    : emailPattern.matcher(s).matches()
                    ? new Result.Success()
                    : new Result.Failure("email " + s + " is invalid.");

    public static void main(String... args) {
        String email1 = "this.is@my.email";
        validate(email1);
        String email2 = null;
        validate(email2);
        String email3 = "";
        validate(email3);
        String email4 = "john.doe@acme.com";
        validate(email4);
    }

    private static void logError(String s) {
        System.err.println("Error message logged: " + s);
    }

    private static void sendVerificationMail(String s) {
        System.out.println("Mail sent to " + s);
    }

    static void validate(String s) {
        Result result = emailChecker.apply(s);
        result.exc(new ResultExecutable() {
            @Override
            public void successExc() {
                sendVerificationMail(s);
            }

            @Override
            public void failureExc() {
                logError(((Result.Failure)result).getMessage());
            }
        });
    }

}
