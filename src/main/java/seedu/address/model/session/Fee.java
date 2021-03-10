package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Session's fee per hour
 * Guarantees: immutable;
 */
public class Fee {

    private static final String VALIDATION_REGEX = "([1-9]\\d+|\\d)(\\.\\d{0,2}|)";
    private static final String MESSAGE_CONSTRAINTS = "Format of fee input is incorrect.";

    private double fee;

    /**
     * Constructs a {@code Fee}.
     *
     * @param value The fee per hour for that specified session
     */
    public Fee(String value) {
        requireNonNull(value);
        checkArgument(isValidFee(value), MESSAGE_CONSTRAINTS);
        double fullFee = Double.parseDouble(value);
        this.fee = fullFee;
    }

    /**
     * Returns true if fee is valid
     */
    public static boolean isValidFee(String value) {
        return value.matches(VALIDATION_REGEX);
    }
}
