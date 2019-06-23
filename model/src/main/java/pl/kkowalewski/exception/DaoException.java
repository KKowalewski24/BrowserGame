package pl.kkowalewski.exception;

import java.io.IOException;

public class DaoException extends IOException {
    public DaoException(final Throwable cause) {
        super(cause);
    }
}
