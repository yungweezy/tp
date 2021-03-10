package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.tuition.Tuition;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Tuition> getTuitionList();

}
