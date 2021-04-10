package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Year;
import seedu.address.model.fee.Month;
import seedu.address.model.session.Duration;
import seedu.address.model.session.Fee;
import seedu.address.model.session.Interval;
import seedu.address.model.session.SessionDate;
import seedu.address.model.session.Subject;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String studyLevel} into a {@code String studyLevel}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code studyLevel} is empty.
     */
    public static String parseStudyLevel(String studyLevel) throws ParseException {
        requireNonNull(studyLevel);
        String trimmedStudyLevel = studyLevel.trim();
        if (trimmedStudyLevel.equals("")) {
            throw new ParseException("Study level can be anything but should not be empty.");
        }
        return trimmedStudyLevel;
    }

    /**
     * Parses a {@code String guardianPhone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parseGuardianPhone(String guardianPhone) throws ParseException {
        requireNonNull(guardianPhone);
        String trimmedPhone = guardianPhone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String relationship} into a {@code String relationship}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code relationship} is empty.
     */
    public static String parseRelationship(String relationship) throws ParseException {
        requireNonNull(relationship);
        String trimmedRelationship = relationship.trim();
        if (trimmedRelationship.equals("")) {
            throw new ParseException("Relationship can be anything but should not be empty.");
        }
        return trimmedRelationship;
    }

    /**
     * Parses {@code String date, String time} into a {@code SessionDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date, time} is invalid.
     */
    public static SessionDate parseDateTime(String date, String time) throws ParseException {
        requireAllNonNull(date, time);
        String trimmedDate = date.trim();
        String trimmedTime = time.trim();
        if (!SessionDate.isValidSessionDate(trimmedDate, trimmedTime)) {
            throw new ParseException(SessionDate.MESSAGE_CONSTRAINTS);
        }

        return new SessionDate(trimmedDate, trimmedTime);
    }

    /**
     * Parses {@code String month} into a {@code Month month}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code month} is invalid.
     */
    public static Month parseMonth(String month) throws ParseException {
        requireAllNonNull(month);
        String trimmedMonth = month.trim();
        int intMonth;

        try {
            intMonth = Integer.parseInt(trimmedMonth);
        } catch (Exception ex) {
            throw new ParseException(Month.MESSAGE_CONSTRAINTS);
        }

        if (!Month.isValidMonth(intMonth)) {
            throw new ParseException(Month.MESSAGE_CONSTRAINTS);
        }

        return new Month(intMonth);
    }

    /**
     * Parses {@code String year} into a {@code Year year}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code year} is invalid.
     */
    public static Year parseYear(String year) throws ParseException {
        requireAllNonNull(year);
        String trimmedYear = year.trim();
        int intYear;

        try {
            intYear = Integer.parseInt(trimmedYear);
        } catch (Exception ex) {
            throw new ParseException(Year.MESSAGE_CONSTRAINTS);
        }

        if (!Year.isValidYear(intYear)) {
            throw new ParseException(Year.MESSAGE_CONSTRAINTS);
        }
        return new Year(intYear);
    }

    /**
     * Parses a {@code String duration} into a {@code Duration}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code duration} is invalid.
     */
    public static Duration parseDuration(String duration) throws ParseException {
        requireNonNull(duration);
        String trimmedDuration = duration.trim();
        if (!Duration.isValidDuration(trimmedDuration)) {
            throw new ParseException(Duration.MESSAGE_CONSTRAINTS);
        }
        return new Duration(trimmedDuration);
    }

    /**
     * Parses a {@code String subject} into a {@code Subject}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code subject} is invalid.
     */
    public static Subject parseSubject(String subject) throws ParseException {
        requireNonNull(subject);
        String trimmedSubject = subject.trim();
        if (!Subject.isValidSubject(trimmedSubject)) {
            throw new ParseException(Subject.MESSAGE_CONSTRAINTS);
        }
        return new Subject(trimmedSubject);
    }

    /**
     * Parses a {@code String fee} into a {@code Fee}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code fee} is invalid.
     */
    public static Fee parseFee(String fee) throws ParseException {
        requireNonNull(fee);
        String trimmedFee = fee.trim();
        if (!Fee.isValidFee(trimmedFee)) {
            throw new ParseException(Fee.MESSAGE_CONSTRAINTS);
        }
        return new Fee(trimmedFee);
    }

    /**
     * Parses a {@code String interval} into a {@code Interval}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code interval} is invalid.
     */
    public static Interval parseInterval(String interval) throws ParseException {
        requireNonNull(interval);
        String trimmedInterval = interval.trim();
        if (!Interval.isValidInterval(trimmedInterval)) {
            throw new ParseException(Interval.MESSAGE_CONSTRAINTS);
        }
        return new Interval(trimmedInterval);
    }
}
