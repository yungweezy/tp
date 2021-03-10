package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a the duration of a session in minutes
 * Guarantees: immutable;
 */
public class Duration {

    public static final String VALIDATION_REGEX = "(0|[1-9]\\d+)";
    public static final String MESSAGE_CONSTRAINTS = "Format of duration input is incorrect.";

    private String value;

    /**
     * Constructs a {@code Duration}.
     *
     * @param value The duration for that session in minutes
     */
    public Duration(String value) {
        requireNonNull(value);
        checkArgument(isValidDuration(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if duration is valid.
     */
    public static boolean isValidDuration(String value) {
        return value.matches(VALIDATION_REGEX);
    }
}
