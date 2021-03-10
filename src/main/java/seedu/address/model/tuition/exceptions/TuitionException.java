package seedu.address.model.tuition.exceptions;

import seedu.address.model.tuition.Tuition;

/**
 * Error during the creation of a tuition {@link Tuition}.
 */
public class TuitionException extends Exception {
    public TuitionException (String message) {
        super(message);
    }

    /**
     * Constructs a new {@code TuitionException} with the specified detail {@code message} and {@code cause}.
     */
    public TuitionException(String message, Throwable cause) {
        super(message, cause);
    }
}
