package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SessionDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        String dateValue = "2021-01-01";
        String timeValue = "23:59";
        assertThrows(NullPointerException.class, () -> new SessionDate(null, null));
        assertThrows(NullPointerException.class, () -> new SessionDate(dateValue, null));
        assertThrows(NullPointerException.class, () -> new SessionDate(null, timeValue));
    }

    @Test
    public void isValidSessionDate() {
        String correctTimeValue = "23:59";

        // checks for empty input
        assertFalse(SessionDate.isValidSessionDate("", correctTimeValue));

        // checks for invalid month
        assertFalse(SessionDate.isValidSessionDate("2021-13-01", correctTimeValue));
        assertFalse(SessionDate.isValidSessionDate("2021-00-01", correctTimeValue));

        // checks for invalid day
        assertFalse(SessionDate.isValidSessionDate("2021-12-00", correctTimeValue));
        assertFalse(SessionDate.isValidSessionDate("2021-12-32", correctTimeValue));

        //checks for 2038 year problem
        assertFalse(SessionDate.isValidSessionDate("2038-12-01", correctTimeValue));
        assertFalse(SessionDate.isValidSessionDate("1969-12-01", correctTimeValue));

        // checks for leap year
        assertFalse(SessionDate.isValidSessionDate("2021-02-29", correctTimeValue));

        // checks for err in date format
        assertFalse(SessionDate.isValidSessionDate("2021-12-001", correctTimeValue));
        assertFalse(SessionDate.isValidSessionDate("2021-012-01", correctTimeValue));
        assertFalse(SessionDate.isValidSessionDate("02021-012-01", correctTimeValue));
        assertFalse(SessionDate.isValidSessionDate("20210-012-01", correctTimeValue));
        assertFalse(SessionDate.isValidSessionDate("2021/12/01", correctTimeValue));
        assertFalse(SessionDate.isValidSessionDate("2021.12.01", correctTimeValue));
    }

    @Test
    public void isValidSessionTime() {
        String correctDateValue = "2021-01-01";

        // checks for empty input
        assertFalse(SessionDate.isValidSessionDate(correctDateValue, ""));

        // checks for correct time format
        assertFalse(SessionDate.isValidSessionDate(correctDateValue, "24:00"));
        assertFalse(SessionDate.isValidSessionDate(correctDateValue, "23:60"));
        assertFalse(SessionDate.isValidSessionDate(correctDateValue, "30:99"));
        assertFalse(SessionDate.isValidSessionDate(correctDateValue, "0000"));
        assertFalse(SessionDate.isValidSessionDate(correctDateValue, "00-00"));
        assertFalse(SessionDate.isValidSessionDate(correctDateValue, "00.00"));
        assertFalse(SessionDate.isValidSessionDate(correctDateValue, "12pm"));
        assertFalse(SessionDate.isValidSessionDate(correctDateValue, "12:52pm"));
        assertFalse(SessionDate.isValidSessionDate(correctDateValue, "09:32am"));
        assertFalse(SessionDate.isValidSessionDate(correctDateValue, "23:002"));
        assertFalse(SessionDate.isValidSessionDate(correctDateValue, "23:592"));
    }

    /**
     * Checks for the validity of the equals method
     */
    @Test
    public void equals() {

        assertTrue(new SessionDate("2020-01-01", "10:30").equals(
                new SessionDate("2020-01-01", "10:30")));

        assertFalse(new SessionDate("2020-01-01", "10:30").equals(
                new SessionDate("2020-01-02", "10:30")));

        assertFalse(new SessionDate("2020-01-01", "10:30").equals(
                new SessionDate("2020-01-01", "10:00")));
    }
}
