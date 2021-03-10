package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FeeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Fee(null));
    }

    @Test
    public void constructor_invalidFee_throwsIllegalArgumentException() {
        String invalidFee = "";
        assertThrows(java.lang.IllegalArgumentException.class, () -> new Fee(invalidFee));
    }

    @Test
    public void isValidFee() {
        // invalid fee formats
        assertFalse(Fee.isValidFee("a"));
        assertFalse(Fee.isValidFee("0.a"));
        assertFalse(Fee.isValidFee("a.0"));
        assertFalse(Fee.isValidFee(""));
        assertFalse(Fee.isValidFee("0.20.2"));
        assertFalse(Fee.isValidFee("$20"));
        assertFalse(Fee.isValidFee("$20.20"));
        assertFalse(Fee.isValidFee("10.230"));
        assertFalse(Fee.isValidFee("005"));
        assertFalse(Fee.isValidFee("005.20"));

        // valid fee formats
        assertTrue(Fee.isValidFee("123456.12"));
        assertTrue(Fee.isValidFee("12.12"));
        assertTrue(Fee.isValidFee("100"));
        assertTrue(Fee.isValidFee("0.50"));
        assertTrue(Fee.isValidFee("0"));
        assertTrue(Fee.isValidFee("2.6"));
        assertTrue(Fee.isValidFee("1000000000000000"));
    }
}
