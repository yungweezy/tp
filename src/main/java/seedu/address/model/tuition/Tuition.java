package seedu.address.model.tuition;

import seedu.address.model.session.Session;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Tuition {

    private final Student student;
    private Session session;

    public Tuition(Name name, Phone phone, Email email, Address address,
                   String studyLevel, Phone guardianPhone, String relationship) {
        requireAllNonNull(name, phone, email, address, guardianPhone, relationship);
        this.student = new Student(name, phone, email, address, studyLevel,
                guardianPhone, relationship);

    }

    public Student getStudent() {
        return student;
    }

    public Session getSession() {
        return session;
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

        if (!(other instanceof  Tuition)) {
            return false;
        }

        Tuition otherTuition = (Tuition) other;
        return otherTuition.student.equals(getStudent());
    }

    @Override
    public String toString() {
        return getStudent().toString();
    }
}
