package seedu.address.model.fee;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.Year;

/**
 * Represents the monthly fee of a particular month and year that is used for the UI.
 */
public class MonthlyFee {

    public static final String MESSAGE_CONSTRAINTS = "Monthly Fee format is incorrect";

    // Data fields
    private double monthlyFee;
    private Month month;
    private Year year;

    /**
     * Constructs a {@code MonthlyFee} object with the respective monthly fee
     * for a particular month and year.
     * @param monthlyFee Monthly fee for this month and year.
     * @param month Month of the monthly fee.
     * @param year Year of the monthly fee.
     */
    public MonthlyFee(double monthlyFee, Month month, Year year) {
        requireAllNonNull(monthlyFee, month, year);
        checkArgument(isValidMonthlyFee(monthlyFee), MESSAGE_CONSTRAINTS);
        this.monthlyFee = monthlyFee;
        this.month = month;
        this.year = year;
    }

    private Boolean isValidMonthlyFee(double monthlyFee) {
        return monthlyFee >= 0;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public Month getMonth() {
        return month;
    }

    public Year getYear() {
        return year;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MonthlyFee)) {
            return false;
        }

        MonthlyFee otherMonthFee = (MonthlyFee) other;
        return otherMonthFee.getMonthlyFee() == getMonthlyFee()
            && otherMonthFee.getMonth() == getMonth()
            && otherMonthFee.getYear() == getYear();
    }
}
