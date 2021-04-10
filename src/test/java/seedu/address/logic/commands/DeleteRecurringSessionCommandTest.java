package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SESSION;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.session.RecurringSession;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionDate;
import seedu.address.model.student.Student;
import seedu.address.testutil.RecurringSessionBuilder;
import seedu.address.testutil.SessionBuilder;
import seedu.address.testutil.StudentBuilder;

class DeleteRecurringSessionCommandTest {
    private static final String VALID_TIME = "12:00";
    private static final String VALID_START_DATE_A = "2021-04-01";
    private static final String VALID_START_DATE_B = "2021-04-02";
    private static final String VALID_END_DATE_A = "2021-05-06";
    private static final String VALID_END_DATE_B = "2021-05-07";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void constructor_nullSession_throwsNullPointerException() {

        Student student = model.getAddressBook().getStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        RecurringSession recurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_START_DATE_A, VALID_TIME)
                        .withLastSessionDate(VALID_END_DATE_A, VALID_TIME).build();
        SessionDate sessionDate = recurringSession.getSessionDate().addDays(recurringSession.getInterval().getValue());
        student.addSession(recurringSession);
        Index sessionIndex = Index.fromOneBased(student.getListOfSessions().size());

        // all null -> returns false
        assertThrows(NullPointerException.class, () -> new DeleteRecurringSessionCommand(null, null, null));
        // valid student name, rest null -> returns false
        assertThrows(NullPointerException.class, (
            ) -> new DeleteRecurringSessionCommand(student.getName(), null, null));
        // valid student name, valid session index, null session date -> returns false
        assertThrows(NullPointerException.class, (
            ) -> new DeleteRecurringSessionCommand(student.getName(), sessionIndex, null));
        // null student name, valid session index, valid session date -> returns false
        assertThrows(NullPointerException.class, (
            ) -> new DeleteRecurringSessionCommand(null, sessionIndex, sessionDate));
        // valid student name, null session index, valid session date -> returns false
        assertThrows(NullPointerException.class, (
            ) -> new DeleteRecurringSessionCommand(student.getName(), null, sessionDate));

    }

    @Test
    void execute_validNameValidIndexValidDate_success() {
        AddressBook addressBook = new AddressBook();
        AddressBook expectedAddressBook = new AddressBook();
        Student alice = new StudentBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withStudyLevel("Sec 2").withGuardianPhone("82813844")
                .withRelationship("Mother")
                .build();
        Student expectedAlice = new StudentBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withStudyLevel("Sec 2").withGuardianPhone("82813844")
                .withRelationship("Mother")
                .build();
        alice.getListOfSessions().clear();
        expectedAlice.getListOfSessions().clear();
        Session sessionToDelete = new SessionBuilder().withSessionDate("2021-04-15", VALID_TIME).build();
        RecurringSession recurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_START_DATE_A, VALID_TIME)
                        .withLastSessionDate(VALID_END_DATE_A, VALID_TIME).build();
        RecurringSession expectedRecurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_START_DATE_A, VALID_TIME)
                        .withLastSessionDate(VALID_END_DATE_A, VALID_TIME).build();
        alice.addSession(recurringSession);
        expectedAlice.addSession(expectedRecurringSession);
        addressBook.addStudent(alice);
        expectedAddressBook.addStudent(expectedAlice);
        Model testingModel = new ModelManager(addressBook, new UserPrefs());
        Model expectedModel = new ModelManager(expectedAddressBook, new UserPrefs());
        String expectedMessage =
                String.format(DeleteRecurringSessionCommand.MESSAGE_DELETE_SESSION_OF_RECURRING_SESSION_SUCCESS,
                        expectedRecurringSession.toString(), sessionToDelete.getSessionDate().getDate().toString());
        expectedModel.deleteSessionInRecurringSession(expectedAlice.getName(),
                INDEX_FIRST_SESSION, sessionToDelete.getSessionDate());
        DeleteRecurringSessionCommand deleteRecurringSessionCommand =
                new DeleteRecurringSessionCommand(alice.getName(),
                        INDEX_FIRST_SESSION, sessionToDelete.getSessionDate());
        assertCommandSuccess(deleteRecurringSessionCommand, testingModel, expectedMessage, expectedModel);
    }

    @Test
    void equals() {
        RecurringSession recurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_START_DATE_A, VALID_TIME)
                        .withLastSessionDate(VALID_END_DATE_A, VALID_TIME).build();
        RecurringSession anotherRecurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_START_DATE_B, VALID_TIME)
                        .withLastSessionDate(VALID_END_DATE_B, VALID_TIME).build();
        Student student = model.getAddressBook().getStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student anotherStudent = model.getAddressBook().getStudentList()
                .get(INDEX_SECOND_STUDENT.getZeroBased());
        student.addSession(recurringSession);
        anotherStudent.addSession(anotherRecurringSession);

        DeleteRecurringSessionCommand deleteRecurringSessionCommand =
                new DeleteRecurringSessionCommand(student.getName(),
                        Index.fromOneBased(student.getListOfSessions().size()), recurringSession.getSessionDate());
        DeleteRecurringSessionCommand anotherDeleteRecurringSessionCommand =
                new DeleteRecurringSessionCommand(anotherStudent.getName(),
                        Index.fromOneBased(anotherStudent.getListOfSessions().size()),
                        anotherRecurringSession.getSessionDate());

        // same object -> returns true
        assertTrue(deleteRecurringSessionCommand.equals(deleteRecurringSessionCommand));

        // different types -> returns false
        assertFalse(deleteRecurringSessionCommand.equals(1));

        // null -> returns false
        assertFalse(deleteRecurringSessionCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteRecurringSessionCommand.equals(anotherDeleteRecurringSessionCommand));
    }
}
