package pl.kkowalewski.exception;

public class BadFieldValueException extends IllegalArgumentException {
    public BadFieldValueException(final String message) {
        super(message);
    }
}
