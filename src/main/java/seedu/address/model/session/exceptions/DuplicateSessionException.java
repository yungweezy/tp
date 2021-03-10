package seedu.address.model.session.exceptions;

/**
 * Signals that the operation will result in duplicate session (Sessions are considered duplicates if they have the same
 * session date).
 */
public class DuplicateSessionException extends RuntimeException {
    public DuplicateSessionException() {
        super("Operation would result in duplicate session");
    }
}
