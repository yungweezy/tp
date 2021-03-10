package seedu.address.model.session;

import seedu.address.model.student.Student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents an individual tuition session at one time slot for one student
 */
public class Session {

    private SessionDate sessionDate;
    private Duration duration;
    private Subject subject;
    private Fee fee;

    /**
     * Constructs a {@code Session}.
     *
     * Requires that all fields entered to be non null
     */
    public Session(SessionDate sessionDate, Duration duration, Subject subject, Fee fee) {
        requireAllNonNull(sessionDate, duration, subject, fee);
        this.sessionDate = sessionDate;
        this.duration = duration;
        this.subject = subject;
        this.fee = fee;
    }

    public SessionDate getSessionDate() {
        return sessionDate;
    }

    public Duration getDuration() {
        return duration;
    }

    public Subject getSubject() {
        return subject;
    }

    public Fee getFee() {
        return fee;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameSession(Session otherSession) {
        if (otherSession == this) {
            return true;
        }

        return otherSession != null
                && otherSession.getSessionDate().equals(getSessionDate());
    }


}
