package seedu.address.model.tuition;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.TypicalTuition.ALICE;
import static seedu.address.testutil.TypicalTuition.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TuitionBuilder;

public class TuitionTest {

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameTuition(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameTuition(null));

        // same name, all other attributes different -> returns true
        Tuition editedAlice = new TuitionBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(ALICE.isSameTuition(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new TuitionBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameTuition(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Tuition editedBob = new TuitionBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameTuition(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new TuitionBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameTuition(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Tuition aliceCopy = new TuitionBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different tuition -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Tuition editedAlice = new TuitionBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new TuitionBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new TuitionBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new TuitionBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
