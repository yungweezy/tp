package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.tuition.Tuition;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Tuition> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a tuition with the same identity as {@code tuition} exists in the address book.
     */
    boolean hasTuition(Tuition tuition);

    /**
     * Deletes the given tuition.
     * The tuition must exist in the address book.
     */
    void deleteTuition(Tuition target);

    /**
     * Adds the given tuition.
     * {@code tuition} must not already exist in the address book.
     */
    void addTuition(Tuition tuition);

    /**
     * Replaces the given tuition {@code target} with {@code editedTuition}.
     * {@code target} must exist in the address book.
     * The tuition identity of {@code editedTuition} must not be the same as another
     * existing tuition in the address book.
     */
    void setTuition(Tuition target, Tuition editedTuition);

    /** Returns an unmodifiable view of the filtered tuition list */
    ObservableList<Tuition> getFilteredTuitionList();

    /**
     * Updates the filter of the filtered tuition list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTuitionList(Predicate<Tuition> predicate);
}
