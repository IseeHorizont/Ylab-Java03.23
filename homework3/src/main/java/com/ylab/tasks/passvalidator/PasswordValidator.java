package homework3.src.main.java.com.ylab.tasks.passvalidator;

import java.util.function.IntPredicate;


public class PasswordValidator {

    public static boolean isPasswordValid(String login, String password,
                                          String confirmPassword) {

        if (login == null || login.trim().isEmpty()) {
            System.out.println("Логин пустой или null");
            return false;
        } else if (password == null || password.trim().isEmpty()) {
            System.out.println("Пароль пустой или null");
            return false;
        } else if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            System.out.println("Строка подтверждения пароля пустая или null");
            return false;
        }

        try {
            IntPredicate isLatinLetter =
                    letter -> ((letter >= 65 && letter <= 90) || (letter >= 97 && letter <= 122));
            IntPredicate isDigit = Character::isDigit;
            IntPredicate isUnderline = symbol -> symbol == 95;

            boolean isWrongSymbolInLogin = login.chars()
                    .anyMatch((isLatinLetter.or(isDigit).or(isUnderline)).negate());
            if (isWrongSymbolInLogin) {
                throw new WrongLoginException("Логин содержит недопустимые символы");
            }

            if (login.length() >= 20) {
                throw new WrongLoginException("Логин слишком длинный");
            }

            boolean isWrongSymbolInPassword = password.chars()
                    .anyMatch((isLatinLetter.or(isDigit).or(isUnderline)).negate());
            if (isWrongSymbolInPassword) {
                throw new WrongPasswordException("Пароль содержит недопустимые символы");
            }

            if (password.length() >= 20) {
                throw new WrongPasswordException("Пароль слишком длинный");
            }

            if (!password.equals(confirmPassword)) {
                throw new WrongPasswordException("Пароль и подтверждение не совпадают");
            }

            return true;
        }
        catch (WrongLoginException | WrongPasswordException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
