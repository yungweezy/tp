package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DurationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Duration(null));
    }

    @Test
    public void constructor_invalidDuration_throwsIllegalArgumentException() {
        String invalidDuration = "";
        assertThrows(java.lang.IllegalArgumentException.class, () -> new Fee(invalidDuration));
    }

    @Test
    public void isValidDuration() {
        // invalid duration formats
        assertFalse(Duration.isValidDuration(""));
        assertFalse(Duration.isValidDuration("1.5"));
        assertFalse(Duration.isValidDuration("1.5.2"));
        assertFalse(Duration.isValidDuration("0.50"));
        assertFalse(Duration.isValidDuration("-10"));
        assertFalse(Duration.isValidDuration("005"));
        assertFalse(Duration.isValidDuration("1/2"));
        assertFalse(Duration.isValidDuration("1.5h"));
        assertFalse(Duration.isValidDuration("20min"));

        // valid duration formats
        assertTrue(Duration.isValidDuration("20"));
        assertTrue(Duration.isValidDuration("0"));
        assertTrue(Duration.isValidDuration("45298345293"));
    }
}
