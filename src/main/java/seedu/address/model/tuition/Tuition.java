package seedu.address.model.tuition;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.session.Session;
import seedu.address.model.session.exceptions.DuplicateSessionException;
import seedu.address.model.session.exceptions.SessionException;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;

public class Tuition {

    private final Student student;
    private ObservableList<Session> sessions;

    public Tuition(Name name, Phone phone, Email email, Address address,
                   String studyLevel, Phone guardianPhone, String relationship) {
        requireAllNonNull(name, phone, email, address, guardianPhone, relationship);
        this.student = new Student(name, phone, email, address, studyLevel,
                guardianPhone, relationship);
        this.sessions = FXCollections.observableArrayList();

    }

    public Name getName() {
        return getStudent().getName();
    }

    public Phone getPhone() {
        return getStudent().getPhone();
    }

    public Email getEmail() {
        return getStudent().getEmail();
    }

    public Address getAddress() {
        return getStudent().getAddress();
    }

    public String getStudyLevel() {
        return getStudent().getStudyLevel();
    }

    public Phone getGuardianPhone() {
        return getStudent().getGuardianPhone();
    }

    public String getRelationship() {
        return getStudent().getRelationship();
    }

    public Student getStudent() {
        return this.student;
    }

    public ObservableList<Session> getSession() {
        return this.sessions;
    }

    /**
     * Returns true if the list contains an equivalent tuition session where both session dates are equal.
     */
    public boolean contains(Session toCheck) {
        requireNonNull(toCheck);
        return getSession().stream().anyMatch(toCheck::isSameSession);
    }

    /**
     * Adds a session to the tuition's list of sessions.
     * @param session Session to be added.
     * @throws SessionException is thrown when duplicate session is found in the current list of sessions.
     */
    public void addSession(Session session) throws SessionException {
        requireNonNull(session);
        if (contains(session)) {
            throw new DuplicateSessionException();
        }
        getSession().add(session);
    }

    /**
     * Returns true if both tuition student have the same name.
     * This defines a weaker notion of equality between two tuition objects.
     */
    public boolean isSameTuition(Tuition otherTuition) {
        if (otherTuition == this) {
            return true;
        }

        return otherTuition != null
                && otherTuition.getStudent().equals(getStudent());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tuition)) {
            return false;
        }

        Tuition otherTuition = (Tuition) other;
        return otherTuition.getStudent().equals(getStudent());
    }

    @Override
    public String toString() {
        return getStudent().toString();
    }
}
