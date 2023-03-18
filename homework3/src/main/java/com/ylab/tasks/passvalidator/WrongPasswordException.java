package homework3.src.main.java.com.ylab.tasks.passvalidator;

public class WrongPasswordException extends Exception {

    public WrongPasswordException() {
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}
