package homework3.src.main.java.com.ylab.tasks.passvalidator;

public class WrongLoginException extends Exception {

    public WrongLoginException() {
    }

    public WrongLoginException(String message) {
        super(message);
    }
}
