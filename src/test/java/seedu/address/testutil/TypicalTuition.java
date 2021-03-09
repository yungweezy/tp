package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GUARDIAN_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GUARDIAN_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RELATIONSHIP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RELATIONSHIP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDY_LEVEL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDY_LEVEL_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.student.Student;
import seedu.address.model.tuition.Tuition;

/**
 * A utility class containing a list of {@code Tuition} objects to be used in tests.
 */
public class TypicalTuition {

    public static final Tuition ALICE = new TuitionBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withStudyLevel("Sec 2").withGuardianPhone("82813844")
            .withRelationship("Mother")
            .build();
    public static final Tuition BENSON = new TuitionBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withStudyLevel("P2").withGuardianPhone("81902144").withRelationship("Father")
            .build();
    public static final Tuition CARL = new TuitionBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withStudyLevel("Uni CS1101S").withGuardianPhone("98621032").withRelationship("Mother").build();
    public static final Tuition DANIEL = new TuitionBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withStudyLevel("JC 2").withGuardianPhone("97213021").withRelationship("Father")
            .build();
    public static final Tuition ELLE = new TuitionBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withStudyLevel("Primary 2").withGuardianPhone("92134012").withRelationship("Mother")
            .build();
    public static final Tuition FIONA = new TuitionBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withStudyLevel("Sec 2").withGuardianPhone("99021234").withRelationship("Mother")
            .build();
    public static final Tuition GEORGE = new TuitionBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withStudyLevel("Sec 3").withGuardianPhone("87620000").withRelationship("Father")
            .build();

    // Manually added
    public static final Tuition HOON = new TuitionBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            .withStudyLevel("Sec 4").withGuardianPhone("98110022").withRelationship("Father").build();
    public static final Tuition IDA = new TuitionBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withStudyLevel("JC 4").withGuardianPhone("87621200").withRelationship("Uncle").build();

    // Manually added - Tuition's details found in {@code CommandTestUtil}
    public static final Tuition AMY = new TuitionBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withStudyLevel(VALID_STUDY_LEVEL_AMY)
            .withGuardianPhone(VALID_GUARDIAN_PHONE_AMY).withRelationship(VALID_RELATIONSHIP_AMY)
            .build();
    public static final Tuition BOB = new TuitionBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withStudyLevel(VALID_STUDY_LEVEL_BOB)
            .withGuardianPhone(VALID_GUARDIAN_PHONE_BOB).withRelationship(VALID_RELATIONSHIP_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTuition() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Tuition tuition : getTypicalPersons()) {
            ab.addTuition(tuition);
        }
        return ab;
    }

    public static List<Tuition> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
