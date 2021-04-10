package seedu.address.model;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the year of the fee.
 */
public class Year {
    public static final String MESSAGE_CONSTRAINTS = "Format of year input is incorrect. "
        + "(Must be between year 1970 to 2037)";

    private int year;

    /**
     * Constructs a {@code Year}.
     *
     * @param year The year to get the fees from.
     */
    public Year(int year) {
        checkArgument(isValidYear(year), MESSAGE_CONSTRAINTS);
        this.year = year;
    }

    @Override
    public String toString() {
        return String.valueOf(year);
    }

    public int getYear() {
        return year;
    }

    /**
     * Returns true if year is valid.
     */
    public static boolean isValidYear(int year) {
        // Do not allow a year that is below 1970 or above 2037 to avoid possible 2038 problem
        return year >= 1970 && year <= 2037;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Year)) {
            return false;
        }

        Year otherYear = (Year) other;
        return otherYear.getYear() == getYear();
    }
}
