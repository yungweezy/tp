package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.session.RecurringSession;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionDate;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

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
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the address book.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the address book.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another
     * existing student in the address book.
     */
    void setStudent(Student target, Student editedStudent);

    /**
     * Returns student with exact name.
     */
    Student getStudentWithName(Name name);

    /**
     * Adds the session to the target student
     */
    void addSession(Name name, Session session);

    /**
     * Deletes the given session.
     * The student must exist in the address book.
     */
    void deleteSession(Name name, Index sessionIndex);

    /**
     * Deletes the given session from a recurring session.
     * The student must exist in the address book and the session must be a recurring session.
     */
    void deleteSessionInRecurringSession(Name name, Index sessionIndex, SessionDate sessionDate);

    /**
     * Returns true if a student with this name exists in the unique student list
     */
    boolean hasName(Name name);

    /**
     * Returns true if {@code session} exists in any of the students in the unique student list
     */
    boolean hasSession(Session session);

    /**
     * Returns true if {@code session} overlaps with any session in any of the students in the unique student list
     */
    boolean hasOverlappingSession(Session session);

    /**
     * Returns true if {@code recurringSession} overlaps with any session in any of the students in the unique
     * student list
     */
    boolean hasOverlappingSession(RecurringSession recurringSession);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * Get the predicate of the current filtered student list.
     * @return The predicate of the current filtered student list.
     */
    Predicate<Student> getFilteredStudentListPredicate();

    /**
     * Gets the total fee between 2 time period  from {@code startPeriod} to {@code endPeriod}
     * @param startPeriod Start of time period
     * @param endPeriod End of time period
     * @return Total fee between the 2 time period
     */
    double getFee(LocalDateTime startPeriod, LocalDateTime endPeriod);
}
