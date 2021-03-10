package seedu.address.model.tuition.exceptions;

import seedu.address.model.student.exceptions.DuplicateStudentException;

/**
 * Signals that the operation will result in duplicate Tuition (Tuition is considered a duplicate if it has the same
 * existing Student).
 */
public class DuplicateTuitionException extends DuplicateStudentException {
    public DuplicateTuitionException() {
        super();
    }
}
