package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import seedu.address.commons.core.index.Index;

/**
 * Class that handles RecurringSession that extend Session.
 */
public class RecurringSession extends Session {
    public static final String MESSAGE_CONSTRAINTS = "Last date does not match starting date and interval.";
    private Interval interval;
    private SessionDate lastSessionDate;

    /**
     * Constructs a {@code RecurringSession}.
     *
     * Requires that all fields entered to be non null.
     * @param sessionDate First sessionDate
     * @param duration Duration of recurring session
     * @param subject Subject of recurring session
     * @param fee Fee of recurring session
     * @param interval Interval by days between recurring sessions.
     * @param lastSessionDate must match sessionDate and interval.
     */
    public RecurringSession(SessionDate sessionDate, Duration duration, Subject subject, Fee fee,
                            Interval interval, SessionDate lastSessionDate) {
        super(sessionDate, duration, subject, fee);
        requireAllNonNull(interval, lastSessionDate);
        this.interval = interval;
        checkArgument(isValidEnd(sessionDate, lastSessionDate, interval), MESSAGE_CONSTRAINTS);
        this.lastSessionDate = lastSessionDate;
    }

    /**
     * Returns true if {@code lastSessionDate} is valid, having consistent Time and Date with
     * {@code firstSessionDate} and {@code interval}.
     * @param firstSessionDate the SessionDate value for first session
     * @param lastSessionDate the SessionDate value for last session
     * @param interval the interval of recurrence
     * @return true if the first session and last session is consistent, based on interval
     */
    public static boolean isValidEnd(SessionDate firstSessionDate, SessionDate lastSessionDate, Interval interval) {
        boolean occursAtTime = firstSessionDate.isSameTime(lastSessionDate);
        boolean occursAtDate = isConsistentDatesAndInterval(firstSessionDate, lastSessionDate, interval);
        return occursAtTime && occursAtDate;
    }

    /**
     * Returns the last valid date for a session to occur on or before the {@dateToTest},
     * if the recurring session starts on {@startDate} and recurs with {@interval} .
     * @param dateToTest a valid date to check for; must not be before startDate
     * @param startDate the start date of the recurring session
     * @param interval the interval of recurrence for the recurring session
     * @return {@LocalDate} of the last valid date of session, on or before {@dateToTest}.
     */
    public static LocalDate lastValidDateOnOrBefore(SessionDate dateToTest, SessionDate startDate, Interval interval) {
        assert(!dateToTest.getDate().isBefore(startDate.getDate()));
        int numOfDaysBetween = startDate.numOfDayTo(dateToTest);
        LocalDate lastLocalDate = dateToTest.getDateTime()
                .minusDays(numOfDaysBetween % interval.getValue()).toLocalDate();
        return lastLocalDate;
    }

    /**
     * Returns true if Date of {@code sessionDate1} is consistent with
     * {@code sessionDate2} and {@code interval}.
     * @param sessionDate1 the SessionDate value for a session
     * @param sessionDate2 the SessionDate value for a session
     * @param interval the interval of recurrence
     * @return true if the session and other session is consistent in terms of Date, based on interval
     */
    public static boolean isConsistentDatesAndInterval(
            SessionDate sessionDate1, SessionDate sessionDate2, Interval interval) {
        requireAllNonNull(sessionDate1, sessionDate2, interval);
        int daysBetween = sessionDate1.numOfDayTo(sessionDate2);
        return daysBetween >= 0 && daysBetween % interval.getValue() == 0;
    }

    public Interval getInterval() {
        return interval;
    }

    public SessionDate getLastSessionDate() {
        return lastSessionDate;
    }

    /**
     * Returns true if recurring session date ends before {@code SessionDate} date.
     */
    public boolean endBefore(SessionDate sessionDate) {
        requireAllNonNull(sessionDate);
        return lastSessionDate.getDate().isBefore(sessionDate.getDate());
    }

    /**
     * Returns true if recurring session date starts after {@code SessionDate} date.
     */
    private boolean startAfter(SessionDate sessionDate) {
        requireAllNonNull(sessionDate);
        return getSessionDate().getDate().isAfter(sessionDate.getDate());
    }

    // THIS METHOD IS FOR SCHEDULE REMINDER TO CHECK IF THERE'S SESSION ON THE DAY.
    /**
     * Returns if session exists on given sessionDate's date.
     * @param sessionDate SessionDate to check if recurring session falls on.
     * @return true if recurring session falls on given sessionDate.
     */
    public boolean hasSessionOnDate(SessionDate sessionDate) {
        requireAllNonNull(sessionDate);
        return !startAfter(sessionDate)
                && !endBefore(sessionDate)
                && isConsistentDatesAndInterval(getSessionDate(), sessionDate, getInterval());
    }

    // THIS METHOD IS FOR SCHEDULE REMINDER TO RETRIEVE INFO ABOUT SESSION HAPPENING ON GIVEN DATE.
    /**
     * Returns a single non-recurring session on the sessionDate.
     * @param sessionDate A valid sessionDate.
     * @return Session of Recurring Session on particular sessionDate.
     */
    private Session buildSessionOnDate(SessionDate sessionDate) {
        requireAllNonNull(sessionDate);
        assert(hasSessionOnDate(sessionDate));
        return new Session(sessionDate, getDuration(), getSubject(), getFee());
    }

    /**
     * Builds a session with the time of the recurring session the specified {@code LocalDate}.
     */
    public Session buildSessionOnDate(LocalDate date) {
        requireNonNull(date);
        SessionDate sessionDate = new SessionDate(date.toString(), getSessionDate().getTime().toString());
        return buildSessionOnDate(sessionDate);
    }

    /**
     * Returns a non-recurring, single session that occurred before a given SessionDate.
     * @param sessionDate A valid date, where a session exists on or before the date.
     * @return A non-recurring single session, most recently past sessionDate.
     */
    public Session lastSessionOnOrBefore(SessionDate sessionDate) {
        requireAllNonNull(sessionDate);
        checkArgument(!startAfter(sessionDate), "There exists no session before.");
        LocalDate lastLocalDate = lastValidDateOnOrBefore(sessionDate, getSessionDate(), interval);
        SessionDate lastSessionDate = new SessionDate(
                LocalDateTime.of(lastLocalDate, getSessionDate().getTime())
                        .toString());
        return this.buildSessionOnDate(lastSessionDate);
    }

    // THIS METHOD IS EXPECTED TO BE USED IN FEE CALCULATION.
    /**
     * Returns the number of session in the span.
     * @param inclusiveStart start of the span
     * @param inclusiveEnd end of the span
     * @return number of sessions occurring
     */
    public int numOfSessionBetween(SessionDate inclusiveStart, SessionDate inclusiveEnd) {
        requireAllNonNull(inclusiveStart, inclusiveEnd);

        if (endBefore(inclusiveStart) || startAfter(inclusiveEnd)) {
            return 0;
        }

        SessionDate lastInSpan;
        if (endBefore(inclusiveEnd)) {
            lastInSpan = lastSessionDate;
        } else {
            lastInSpan = lastSessionOnOrBefore(inclusiveEnd).getSessionDate();
        }

        SessionDate firstInSpan;
        if (startAfter(inclusiveStart)) {
            firstInSpan = getSessionDate();
        } else {
            int daysDiff = getSessionDate().numOfDayTo(inclusiveStart) % interval.getValue();
            int plusDays = daysDiff == 0
                    ? 0
                    : interval.getValue() - daysDiff;
            LocalDateTime firstInSpanLdt = inclusiveStart.getDateTime().plusDays(plusDays);
            firstInSpan = new SessionDate(firstInSpanLdt.toString());
        }

        return (firstInSpan.numOfDayTo(lastInSpan) / interval.getValue()) + 1;
    }

    /**
     * Checks if the {@code Session} slot overlaps with all recurring sessions.
     * @param otherSession the other session that is compared to.
     */
    public boolean isOverlapping(Session otherSession) {
        return otherSession.isOverlapping(this);
    }

    /**
     * Checks if all {@code RecurringSession} slots overlaps with all recurring sessions.
     * @param otherSession the other recurring session that is compared to.
     */
    public boolean isOverlapping(RecurringSession otherSession) {
        if (startAfter(otherSession.getSessionDate())) {
            return otherSession.isOverlapping(this);
        }

        int daysBetween = getSessionDate().numOfDayTo(otherSession.getSessionDate());
        // Recurrence length in terms of when the first session begins
        int firstSessionRecurrenceLength =
                (int) ChronoUnit.DAYS.between(getSessionDate().getDate(), getLastSessionDate().getDate());
        int secondSessionRecurrenceLength =
                (int) ChronoUnit.DAYS.between(otherSession.getSessionDate().getDate(),
                        otherSession.getLastSessionDate().getDate()) + daysBetween;
        // Days from when the earlier session starts
        int daysFromThisSessionStart = 0;
        int daysFromOtherSessionStart = daysBetween;
        while (daysFromThisSessionStart < firstSessionRecurrenceLength
                || daysFromOtherSessionStart < secondSessionRecurrenceLength) {
            if (daysFromThisSessionStart == daysFromOtherSessionStart) {
                break;
            } else if (daysFromThisSessionStart > daysFromOtherSessionStart) {
                if (daysFromOtherSessionStart >= secondSessionRecurrenceLength) {
                    break;
                }
                daysFromOtherSessionStart += otherSession.getInterval().getValue();
            } else {
                if (daysFromThisSessionStart >= firstSessionRecurrenceLength) {
                    break;
                }
                daysFromThisSessionStart += getInterval().getValue();
            }
        }
        if (daysFromThisSessionStart != daysFromOtherSessionStart) {
            return false;
        }
        SessionDate otherSessionStartDate = otherSession.getSessionDate();
        SessionDate otherSessionEndDate = otherSessionStartDate.getEndSessionDate(otherSession.getDuration());
        SessionDate sessionStartDate = getSessionDate();
        SessionDate sessionEndDate = sessionStartDate.getEndSessionDate(getDuration());
        return super.isTimeOverlapping(sessionStartDate, sessionEndDate, otherSessionStartDate, otherSessionEndDate);
    }

    /**
     * Creates a new {@code RecurringSession} with the new {@code newSessionDate}.
     * @param newSessionDate the new session date.
     */
    public RecurringSession withStartDate(SessionDate newSessionDate) {
        return new RecurringSession(newSessionDate, getDuration(), getSubject(),
                getFee(), getInterval(), getLastSessionDate());
    }

    /**
     * Creates a new {@code RecurringSession} with the new {@code newLastSessionDate}.
     * @param newLastSessionDate the new last session date.
     */
    public RecurringSession withLastSessionDate(SessionDate newLastSessionDate) {
        return new RecurringSession(getSessionDate(), getDuration(), getSubject(),
                getFee(), getInterval(), newLastSessionDate);
    }

    /**
     * Removes a single {@code Session} with {@code sessionDate} from this {@code RecurringSession}
     * that is in {@code sessionList} with an index of {@code sessionIndex}.
     */
    public void removeSessionInRecurringSession(Index sessionIndex,
                                                SessionDate sessionDate, List<Session> sessionList) {
        if (getSessionDate().equals(sessionDate)) {
            // if session date is the start of the recurring session
            removeSessionAtStartOfRecurringSession(sessionIndex, sessionList);

        } else if (getLastSessionDate().equals(sessionDate)) {
            // if session date is at the end of the recurring session
            removeSessionAtEndOfRecurringSession(sessionIndex, sessionList);
        } else {
            removeSessionInMiddleOfRecurringSession(sessionIndex, sessionDate, sessionList);
        }
    }

    /**
     * Removes a single {@code Session} with the same starting {@code SessionDate} as this {@code RecurringSession}.
     */
    public void removeSessionAtStartOfRecurringSession(Index sessionIndex, List<Session> sessionList) {
        if (getLastSessionDate().equals(getSessionDate())) {
            sessionList.remove(sessionIndex.getZeroBased());
        } else {
            SessionDate newStartDate = getSessionDate().addDays(getInterval().getValue());
            if (newStartDate.equals(getLastSessionDate())) {
                sessionList.set(sessionIndex.getZeroBased(), buildSessionOnDate(newStartDate));
            } else {
                sessionList.set(sessionIndex.getZeroBased(), withStartDate(newStartDate));
            }
        }
    }

    /**
     * Removes a single {@code Session} with the same ending {@code SessionDate} as this {@code RecurringSession}.
     */
    public void removeSessionAtEndOfRecurringSession(Index sessionIndex, List<Session> sessionList) {
        SessionDate newEndDate = getLastSessionDate().minusDays(getInterval().getValue());
        if (newEndDate.equals(getSessionDate())) {
            sessionList.set(sessionIndex.getZeroBased(), buildSessionOnDate(newEndDate));
        } else {
            sessionList.set(sessionIndex.getZeroBased(), withLastSessionDate(newEndDate));
        }
    }

    /**
     * Removes a single {@code Session} in the middle of this {@code RecurringSession}.
     * Splits the {@code RecurringSession} into two, one {@code RecurringSession}/{@code Session}
     * exclusively before {@code SessionDate}, and another exclusively after {@code SessionDate}.
     */
    public void removeSessionInMiddleOfRecurringSession(Index sessionIndex,
                                                        SessionDate sessionDate, List<Session> sessionList) {
        SessionDate lastSessionDate = getLastSessionDate();
        SessionDate firstSessionEndDate = sessionDate.minusDays(getInterval().getValue());
        if (firstSessionEndDate.equals(getSessionDate())) {
            sessionList.set(sessionIndex.getZeroBased(), buildSessionOnDate(firstSessionEndDate));
        } else {
            sessionList.set(sessionIndex.getZeroBased(), withLastSessionDate(firstSessionEndDate));
        }
        SessionDate secondSessionStartDate = sessionDate.addDays(getInterval().getValue());
        if (secondSessionStartDate.equals(lastSessionDate)) {
            sessionList.add(buildSessionOnDate(secondSessionStartDate));
        } else {
            sessionList.add(new RecurringSession(secondSessionStartDate, getDuration(), getSubject(), getFee(),
                    getInterval(), lastSessionDate));
        }
    }



    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("; Interval: ")
                .append(getInterval())
                .append("; Last Date: ")
                .append(getLastSessionDate().getDate());
        return super.toString() + builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RecurringSession)) {
            return false;
        }

        RecurringSession otherSession = (RecurringSession) other;
        return super.equals(other) // this is intended to extend the parent equals method.
                && otherSession.getInterval().equals(otherSession.getInterval())
                && otherSession.getLastSessionDate().equals(otherSession.getLastSessionDate());

    }
}
