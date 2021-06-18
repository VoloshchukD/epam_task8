package by.epamtc.exception;

public class NoSuchUnitException extends Exception {

    public NoSuchUnitException() {
    }

    public NoSuchUnitException(String message) {
        super(message);
    }

    public NoSuchUnitException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchUnitException(Throwable cause) {
        super(cause);
    }

}
