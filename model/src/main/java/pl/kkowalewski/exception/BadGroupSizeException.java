package pl.kkowalewski.exception;

public class BadGroupSizeException extends IllegalArgumentException {
    public BadGroupSizeException(final String message) {
        super(message);
    }
}
