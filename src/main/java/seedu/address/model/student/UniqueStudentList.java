package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.session.RecurringSession;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionDate;
import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.model.student.exceptions.StudentNotFoundException;

/**
 * A list of students that enforces uniqueness between its elements and does not allow nulls.
 * A student is considered unique by comparing using {@code Student#isSameStudent(Student)}. As such, adding and
 * updating of students uses Student#isSameStudent(Student) for equality so as to ensure that the student being added
 *  or updated is unique in terms of identity in the UniqueStudentList. However, the removal of a student uses
 *  Student#equals(Object) so as to ensure that the student with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Student#isSameStudent(Student)
 */
public class UniqueStudentList implements Iterable<Student> {

    private final ObservableList<Student> internalList = FXCollections.observableArrayList();
    private final ObservableList<Student> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent student as the given argument.
     */
    public boolean contains(Student toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameStudent);
    }

    /**
     * Adds a student to the list.
     * The student must not already exist in the list.
     */
    public void add(Student toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateStudentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the list.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the list.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StudentNotFoundException();
        }

        if (!target.isSameStudent(editedStudent) && contains(editedStudent)) {
            throw new DuplicateStudentException();
        }

        internalList.set(index, editedStudent);
    }

    /**
     * Removes the equivalent student from the list.
     * The student must exist in the list.
     */
    public void remove(Student toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new StudentNotFoundException();
        }
    }

    public void setStudents(UniqueStudentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        requireAllNonNull(students);
        if (!studentsAreUnique(students)) {
            throw new DuplicateStudentException();
        }

        internalList.setAll(students);
    }

    /**
     * Returns the student with the matching name
     * Guarantees that student with such name exists already exists in the address book.
     */
    public Student getStudentWithName(Name name) {
        requireNonNull(name);
        for (int i = 0; i < internalList.size(); i++) {
            if (internalList.get(i).getName().equals(name)) {
                return internalList.get(i);
            }
        }
        return null;
    }

    /**
     * Returns true if {@code name} exists in the unique student list
     */
    public boolean hasName(Name name) {
        Student student = getStudentWithName(name);
        if (student == null) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if target {@code Session} with same date and time exists in any of the students
     * in the unique student list
     */
    public boolean hasSession(Session target) {
        requireNonNull(target);
        SessionDate targetSessionDate = target.getSessionDate();

        for (Student student : internalList) {
            List<Session> sessionList = student.getListOfSessions();
            for (Session session : sessionList) {
                SessionDate sessionDate = session.getSessionDate();
                if (sessionDate.equals(targetSessionDate)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if target {@code Session} overlaps with any of the sessions existing in any student
     * in the unique student list.
     * This method does not check for equality in end of {@code SessionDate} for sessions.
     * Use {@link #hasSession(Session)} method instead.
     */
    public boolean hasOverlappingSession(Session target) {
        requireNonNull(target);

        for (Student student : internalList) {
            List<Session> sessionList = student.getListOfSessions();
            for (Session session : sessionList) {
                if (session instanceof RecurringSession) {
                    RecurringSession recurringSession = (RecurringSession) session;
                    if (recurringSession.isOverlapping(target)) {
                        return true;
                    }
                } else {
                    if (session.isOverlapping(target)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns true if target {@code RecurringSession} overlaps with any of the sessions existing in any student
     * in the unique student list.
     * This method does not check for equality in end of {@code SessionDate} for sessions.
     * Use {@link #hasSession(Session)} method instead.
     */
    public boolean hasOverlappingSession(RecurringSession target) {
        requireNonNull(target);

        for (Student student : internalList) {
            List<Session> sessionList = student.getListOfSessions();
            for (Session session : sessionList) {
                if (session instanceof RecurringSession) {
                    RecurringSession recurringSession = (RecurringSession) session;
                    if (recurringSession.isOverlapping(target)) {
                        return true;
                    }
                } else {
                    if (session.isOverlapping(target)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Adds a {@code Session} to the target {@code Student} in the student list.
     *
     * @param target Target student.
     * @param session Session to be added.
     */
    public void addSession(Student target, Session session) {
        target.addSession(session);
        int index = internalList.indexOf(target);
        internalList.set(index, target);
    }

    /**
     * Deletes a {@code Session} in the target {@code Student} of the student list.
     *
     * @param target Target student.
     * @param sessionIndex Index of session to be deleted.
     */
    public void deleteSession(Student target, Index sessionIndex) {
        target.removeSession(sessionIndex);
        int index = internalList.indexOf(target);
        internalList.set(index, target);
    }

    /**
     * Deletes a {@code Session} from a {@code RecurringSession} in the target {@code Student} in the student list.
     *
     * @param target Target student.
     * @param sessionIndex Index of session to be deleted.
     * @param sessionDate Date to be removed from the recurring session.
     */
    public void deleteSessionInRecurringSession(Student target, Index sessionIndex, SessionDate sessionDate) {
        target.removeSessionInRecurringSession(sessionIndex, sessionDate);
        int index = internalList.indexOf(target);
        internalList.set(index, target);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Student> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Student> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueStudentList // instanceof handles nulls
                        && internalList.equals(((UniqueStudentList) other).internalList)
                        && allSessionEquals(((UniqueStudentList) other).internalList));
    }

    /**
     * Checks if all sessions of each student in {@code internalList} are equal to all sessions
     * of each student in {@code studentList}.
     */
    private boolean allSessionEquals(ObservableList<Student> studentList) {
        for (int i = 0; i < internalList.size(); i++) {
            Student currStudent = internalList.get(i);
            Student otherStudent = studentList.get(i);
            List<Session> currSessionList = currStudent.getListOfSessions();
            List<Session> otherSessionList = otherStudent.getListOfSessions();
            for (int j = 0; j < currSessionList.size(); j++) {
                Session currSession = currSessionList.get(j);
                Session otherSession = otherSessionList.get(j);
                if (!currSession.equals(otherSession)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code students} contains only unique students.
     */
    private boolean studentsAreUnique(List<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).isSameStudent(students.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
