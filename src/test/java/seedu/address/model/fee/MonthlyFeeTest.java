package seedu.address.model.fee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.Year;

public class MonthlyFeeTest {

    private double validMonthlyFee = 30.20;
    private Month validMonth = new Month(3);
    private Year validYear = new Year(2021);

    @Test
    public void constructor_invalidMonthlyFee_throwsNullPointerException() {
        assertThrows(java.lang.NullPointerException.class, () -> new MonthlyFee(validMonthlyFee, validMonth, null));
        assertThrows(java.lang.NullPointerException.class, () -> new MonthlyFee(validMonthlyFee, null, validYear));
    }

    @Test
    public void constructor_invalidMonthlyFee_throwsIllegalArgumentException() {
        assertThrows(java.lang.IllegalArgumentException.class, () -> new MonthlyFee(-20.0, validMonth, validYear));
    }

    @Test
    public void equals() {
        MonthlyFee monthlyFee = new MonthlyFee(validMonthlyFee, validMonth, validYear);
        MonthlyFee monthlyFeeCopy = new MonthlyFee(validMonthlyFee, validMonth, validYear);

        // same values -> returns true
        assertTrue(monthlyFee.equals(monthlyFeeCopy));

        // same object -> returns true
        assertTrue(monthlyFee.equals(monthlyFee));

        // null -> returns false
        assertFalse(monthlyFee.equals(null));

        // different type -> returns false
        assertFalse(monthlyFee.equals(monthlyFeeCopy.getMonthlyFee()));

        // different monthly fee -> returns false
        assertFalse(monthlyFee.equals(new MonthlyFee(20.0, validMonth, validYear)));

        // different month -> returns false
        assertFalse(monthlyFee.equals(new MonthlyFee(validMonthlyFee, new Month(10), validYear)));

        // different year -> returns false
        assertFalse(monthlyFee.equals(new MonthlyFee(validMonthlyFee, validMonth, new Year(2020))));
    }
}
