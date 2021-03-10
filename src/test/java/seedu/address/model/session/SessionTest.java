package seedu.address.model.session;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.session.exceptions.SessionException;

public class SessionTest {

    @Test
    public void constructor_null_throwsNullPointerException() throws SessionException {
        String validSessionDate = "2020-01-01";
        String validSessionTime = "10:00";
        String validDuration = "120";
        String validSubject = "Math";
        String validFee = "40";
        SessionDate sessionDate = new SessionDate(validSessionDate, validSessionTime);
        Duration duration = new Duration(validDuration);
        Subject subject = new Subject(validSubject);
        Fee fee = new Fee(validFee);
        assertThrows(NullPointerException.class, () -> new Session(null, null, null, null));
        assertThrows(NullPointerException.class, () -> new Session(sessionDate, duration, subject, null));
        assertThrows(NullPointerException.class, () -> new Session(sessionDate, duration, null, fee));
        assertThrows(NullPointerException.class, () -> new Session(sessionDate, null, subject, fee));
        assertThrows(NullPointerException.class, () -> new Session(null, duration, subject, fee));
    }

}
