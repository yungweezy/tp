package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTuition.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTuition.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;

public class JsonAdaptedTuitionTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    // private static final String INVALID_GUARDIAN_PHONE = "+1234";
    // private static final String INVALID_RELATIONSHIP = ""; //TODO

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_STUDY_LEVEL = BENSON.getStudyLevel();
    private static final String VALID_GUARDIAN_PHONE = BENSON.getGuardianPhone().toString();
    private static final String VALID_RELATIONSHIP = BENSON.getRelationship();


    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedTuition person = new JsonAdaptedTuition(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTuition person =
                new JsonAdaptedTuition(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                    VALID_STUDY_LEVEL, VALID_GUARDIAN_PHONE, VALID_RELATIONSHIP);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTuition person = new JsonAdaptedTuition(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
            VALID_STUDY_LEVEL, VALID_GUARDIAN_PHONE, VALID_RELATIONSHIP);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedTuition person =
                new JsonAdaptedTuition(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                    VALID_STUDY_LEVEL, VALID_GUARDIAN_PHONE, VALID_RELATIONSHIP);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedTuition person = new JsonAdaptedTuition(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
            VALID_STUDY_LEVEL, VALID_GUARDIAN_PHONE, VALID_RELATIONSHIP);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedTuition person =
                new JsonAdaptedTuition(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                    VALID_STUDY_LEVEL, VALID_GUARDIAN_PHONE, VALID_RELATIONSHIP);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedTuition person = new JsonAdaptedTuition(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
            VALID_STUDY_LEVEL, VALID_GUARDIAN_PHONE, VALID_RELATIONSHIP);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedTuition person =
                new JsonAdaptedTuition(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                    VALID_STUDY_LEVEL, VALID_GUARDIAN_PHONE, VALID_RELATIONSHIP);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedTuition person = new JsonAdaptedTuition(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
            VALID_STUDY_LEVEL, VALID_GUARDIAN_PHONE, VALID_RELATIONSHIP);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

}
