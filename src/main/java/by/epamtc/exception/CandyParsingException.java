package by.epamtc.exception;

public class CandyParsingException extends Exception {

    public CandyParsingException() {
    }

    public CandyParsingException(String message) {
        super(message);
    }

    public CandyParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public CandyParsingException(Throwable cause) {
        super(cause);
    }

}
