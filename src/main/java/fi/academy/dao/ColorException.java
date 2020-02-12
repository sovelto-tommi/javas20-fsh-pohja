package fi.academy.dao;

public class ColorException extends RuntimeException {
    public ColorException() {
    }

    public ColorException(String message) {
        super(message);
    }

    public ColorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ColorException(Throwable cause) {
        super(cause);
    }
}
