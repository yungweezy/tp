package seedu.address.model.tuition;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.exceptions.StudentNotFoundException;
import seedu.address.model.tuition.exceptions.DuplicateTuitionException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A student is considered unique by comparing using {@code Student#isSameStudent(Student)}. As such, adding and
 * updating of persons uses Student#isSameStudent(Student) for equality so as to ensure that the student being added
 *  or updated is unique in terms of identity in the UniqueStudentList. However, the removal of a student uses
 *  Student#equals(Object) so as to ensure that the student with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Tuition#isSameTuition(Tuition)
 */
public class UniqueTuitionList implements Iterable<Tuition> {

    private final ObservableList<Tuition> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tuition> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tuition student as the given argument.
     */
    public boolean contains(Tuition toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTuition);
    }

    /**
     * Adds a student to the list.
     * The student must not already exist in the list.
     */
    public void add(Tuition toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTuitionException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the list.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the list.
     */
    public void setTuition(Tuition target, Tuition editedTuition) {
        requireAllNonNull(target, editedTuition);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StudentNotFoundException();
        }

        if (!target.isSameTuition(editedTuition) && contains(editedTuition)) {
            throw new DuplicateTuitionException();
        }

        internalList.set(index, editedTuition);
    }

    /**
     * Removes the equivalent student from the list.
     * The student must exist in the list.
     */
    public void remove(Tuition toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new StudentNotFoundException();
        }
    }

    public void setTuitionList(UniqueTuitionList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setTuitionList(List<Tuition> tuitionList) {
        requireAllNonNull(tuitionList);
        if (!tuitionListIsUnique(tuitionList)) {
            throw new DuplicateTuitionException();
        }

        internalList.setAll(tuitionList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Tuition> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Tuition> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTuitionList // instanceof handles nulls
                        && internalList.equals(((UniqueTuitionList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code students} contains only unique students.
     */
    private boolean tuitionListIsUnique(List<Tuition> tuitionList) {
        for (int i = 0; i < tuitionList.size() - 1; i++) {
            for (int j = i + 1; j < tuitionList.size(); j++) {
                if (tuitionList.get(i).isSameTuition(tuitionList.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
