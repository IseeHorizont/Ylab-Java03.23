package homework3.src.main.java.com.ylab.tasks.passvalidator;

import static homework3.src.main.java.com.ylab.tasks.passvalidator.PasswordValidator.isPasswordValid;

public class PasswordValidatorTest {

    public static void main(String[] args) {

        // valid data
        System.out.println(
                isPasswordValid("Log1n_" , "Pa_55word", "Pa_55word")
        );

        System.out.println(
                isPasswordValid("12_Login" , "Password_7", "Password_7")
        );

        // wrong login
        System.out.println(
                isPasswordValid("Log1n_#" , "Pass_word", "Pass_word")
        );

        // too long login
        System.out.println(
                isPasswordValid("LoginIsLongerThanTwenty" , "Pass55", "Pass55")
        );

        // wrong password
        System.out.println(
                isPasswordValid("Log_in2" , "Pa$$word", "Pa$$word")
        );

        // too long password
        System.out.println(
                isPasswordValid("Login11", "PasswordIsLongerThanTwenty", "Password")
        );

        // different password & confirmPassword
        System.out.println(
                isPasswordValid("Login_32", "Password", "OtherPassword")
        );

        // something is null
        System.out.println(
                isPasswordValid(null, "Password", "OtherPassword")
        );

        System.out.println(
                isPasswordValid("Login_32", null, "OtherPassword")
        );

        System.out.println(
                isPasswordValid("Login_32", "Password", null)
        );

        // something of data is empty
        System.out.println(
                isPasswordValid("     ", "Password", "OtherPassword")
        );

        System.out.println(
                isPasswordValid("Login_32", "    ", "OtherPassword")
        );

        System.out.println(
                isPasswordValid("Login_32", "Password", "")
        );
    }
}
