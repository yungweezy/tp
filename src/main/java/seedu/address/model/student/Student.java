package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.model.session.RecurringSession;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionDate;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final String studyLevel;
    private final Phone guardianPhone;
    private final String relationship;

    // Session field
    private List<Session> sessions;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Address address, String studyLevel, Phone guardianPhone,
            String relationship) {
        requireAllNonNull(name, phone, email, address, studyLevel, guardianPhone, relationship);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.studyLevel = studyLevel;
        this.guardianPhone = guardianPhone;
        this.relationship = relationship;
        this.sessions = new ArrayList<>();
    }

    /**
     * Construct Student with existing sessions
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Address address, String studyLevel, Phone guardianPhone,
                   String relationship, List<Session> sessions) {
        requireAllNonNull(name, phone, email, address, studyLevel, guardianPhone, relationship);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.studyLevel = studyLevel;
        this.guardianPhone = guardianPhone;
        this.relationship = relationship;
        this.sessions = sessions;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getStudyLevel() {
        return studyLevel;
    }

    public Phone getGuardianPhone() {
        return guardianPhone;
    }

    public String getRelationship() {
        return relationship;
    }

    public List<Session> getListOfSessions() {
        return this.sessions;
    }

    /**
     * Adds a tuition session to the student.
     * @param session to be added.
     */
    public void addSession(Session session) {
        requireAllNonNull(session);
        this.sessions.add(session);
    }

    /**
     * Adds a number of sessions to the student.
     * @param sessions to be added.
     */
    public void addSessions(Session...sessions) {
        requireNonNull(sessions);
        this.sessions.addAll(Arrays.asList(sessions));
    }

    /**
     * Deletes a tuition session to the student.
     * @param sessionIndex (one-based) to be removed.
     */
    public void removeSession(Index sessionIndex) {
        requireAllNonNull(sessionIndex);
        this.sessions.remove(sessionIndex.getZeroBased());
    }

    /**
     * Removes a single {@code Session} with {@code sessionDate}.
     * Splits the remaining {@code RecurringSession} into two {@code RecurringSession}.
     * @param sessionIndex (one-based) to be removed.
     */
    public void removeSessionInRecurringSession(Index sessionIndex, SessionDate sessionDate) {
        requireAllNonNull(sessionIndex, sessionDate);
        RecurringSession recurringSession = (RecurringSession) getListOfSessions().get(sessionIndex.getZeroBased());
        recurringSession.removeSessionInRecurringSession(sessionIndex, sessionDate, getListOfSessions());
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName());
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getAddress().equals(getAddress())
                && otherStudent.getStudyLevel().equals(getStudyLevel())
                && otherStudent.getGuardianPhone().equals(getGuardianPhone())
                && otherStudent.getRelationship().equals(getRelationship());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, studyLevel, guardianPhone, relationship);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Study Level: ")
                .append(getStudyLevel())
                .append("; Guardian Phone: ")
                .append(getGuardianPhone())
                .append("; Relationship: ")
                .append(getRelationship());

        if (getListOfSessions().size() > 0) {
            builder.append("; Sessions: ")
                    .append(getListOfSessions());
        }

        return builder.toString();
    }

}
