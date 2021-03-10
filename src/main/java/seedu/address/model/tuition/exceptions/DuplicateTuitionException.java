package seedu.address.model.tuition.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateTuitionException extends RuntimeException {
    public DuplicateTuitionException() {
        super("Operation would result in duplicate student");
    }
}
