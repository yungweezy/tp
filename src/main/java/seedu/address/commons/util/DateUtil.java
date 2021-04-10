package seedu.address.commons.util;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;

import seedu.address.model.Year;
import seedu.address.model.fee.Month;

/**
 * A class for date time manipulation.
 */
public class DateUtil {
    /**
     * Gets the first day of the {@code Month} and {@code Year} in local date time format.
     *
     * @param month Month of the expected local date time.
     * @param year Year of the expected local date time.
     * @return LocalDateTime of the month and year combined.
     */
    public static LocalDateTime getFirstDayOfMonth(Month month, Year year) {
        requireAllNonNull(month, year);
        return LocalDateTime.of(year.getYear(), month.getMonth(), 1, 0, 0);
    }
}
