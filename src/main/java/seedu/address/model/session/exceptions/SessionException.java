package seedu.address.model.session.exceptions;

/**
 * Error during the creation of a session {@link Session}.
 */
public class SessionException extends Exception {
    public SessionException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code SessionException} with the specified detail {@code message} and {@code cause}.
     */
    public SessionException(String message, Throwable cause) {
        super(message, cause);
    }
}
