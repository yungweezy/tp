package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRING_END_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRING_START_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SESSION;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.AMY;
import static seedu.address.testutil.TypicalStudents.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.session.RecurringSession;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionDate;
import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.model.student.exceptions.StudentNotFoundException;
import seedu.address.testutil.RecurringSessionBuilder;
import seedu.address.testutil.SessionBuilder;
import seedu.address.testutil.StudentBuilder;

public class UniqueStudentListTest {

    private final UniqueStudentList uniqueStudentList = new UniqueStudentList();

    @Test
    public void contains_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.contains(null));
    }

    @Test
    public void contains_studentNotInList_returnsFalse() {
        assertFalse(uniqueStudentList.contains(ALICE));
    }

    @Test
    public void contains_studentInList_returnsTrue() {
        uniqueStudentList.add(ALICE);
        assertTrue(uniqueStudentList.contains(ALICE));
    }

    @Test
    public void contains_studentWithSameIdentityFieldsInList_returnsTrue() {
        uniqueStudentList.add(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .build();
        assertTrue(uniqueStudentList.contains(editedAlice));
    }

    @Test
    public void add_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.add(null));
    }

    @Test
    public void add_duplicateStudent_throwsDuplicateStudentException() {
        uniqueStudentList.add(ALICE);
        assertThrows(DuplicateStudentException.class, () -> uniqueStudentList.add(ALICE));
    }

    @Test
    public void setStudent_nullTargetStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudent(null, ALICE));
    }

    @Test
    public void setStudent_nullEditedStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudent(ALICE, null));
    }

    @Test
    public void setStudent_targetStudentNotInList_throwsStudentNotFoundException() {
        assertThrows(StudentNotFoundException.class, () -> uniqueStudentList.setStudent(ALICE, ALICE));
    }

    @Test
    public void setStudent_editedStudentIsSameStudent_success() {
        uniqueStudentList.add(ALICE);
        uniqueStudentList.setStudent(ALICE, ALICE);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(ALICE);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudent_editedStudentHasSameIdentity_success() {
        uniqueStudentList.add(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .build();
        uniqueStudentList.setStudent(ALICE, editedAlice);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(editedAlice);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudent_editedStudentHasDifferentIdentity_success() {
        uniqueStudentList.add(ALICE);
        uniqueStudentList.setStudent(ALICE, BOB);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(BOB);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudent_editedStudentHasNonUniqueIdentity_throwsDuplicateStudentException() {
        uniqueStudentList.add(ALICE);
        uniqueStudentList.add(BOB);
        assertThrows(DuplicateStudentException.class, () -> uniqueStudentList.setStudent(ALICE, BOB));
    }

    @Test
    public void remove_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.remove(null));
    }

    @Test
    public void remove_studentDoesNotExist_throwsStudentNotFoundException() {
        assertThrows(StudentNotFoundException.class, () -> uniqueStudentList.remove(ALICE));
    }

    @Test
    public void remove_existingStudent_removesStudent() {
        uniqueStudentList.add(ALICE);
        uniqueStudentList.remove(ALICE);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudents_nullUniqueStudentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudents((UniqueStudentList) null));
    }

    @Test
    public void setStudents_uniqueStudentList_replacesOwnListWithProvidedUniqueStudentList() {
        uniqueStudentList.add(ALICE);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(BOB);
        uniqueStudentList.setStudents(expectedUniqueStudentList);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudents_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudents((List<Student>) null));
    }

    @Test
    public void setStudents_list_replacesOwnListWithProvidedList() {
        uniqueStudentList.add(ALICE);
        List<Student> studentList = Collections.singletonList(BOB);
        uniqueStudentList.setStudents(studentList);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(BOB);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudents_listWithDuplicateStudents_throwsDuplicateStudentException() {
        List<Student> listWithDuplicateStudents = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateStudentException.class, () -> uniqueStudentList.setStudents(listWithDuplicateStudents));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueStudentList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void hasName_nameDoesNotExist_returnsFalse() {
        uniqueStudentList.add(ALICE);
        assertFalse(uniqueStudentList.hasName(BOB.getName()));
    }

    @Test
    public void hasName_nameExists_returnsTrue() {
        uniqueStudentList.add(ALICE);
        assertTrue(uniqueStudentList.hasName(ALICE.getName()));
    }

    @Test
    public void removeSessionInRecurringSession_validRemoveSessionAtSessionStart() {
        uniqueStudentList.add(AMY);
        uniqueStudentList.add(BOB);
        AMY.getListOfSessions().clear();
        BOB.getListOfSessions().clear();
        RecurringSession recurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_RECURRING_START_DATE_AMY, VALID_TIME)
                        .withLastSessionDate(VALID_RECURRING_END_DATE_AMY, VALID_TIME).build();
        AMY.addSession(recurringSession);
        RecurringSession anotherRecurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_RECURRING_START_DATE_AMY, VALID_TIME)
                        .withLastSessionDate(VALID_RECURRING_END_DATE_AMY, VALID_TIME).build();
        anotherRecurringSession = anotherRecurringSession.withStartDate(anotherRecurringSession
                .getSessionDate().addDays(7));
        BOB.addSession(anotherRecurringSession);
        SessionDate sessionDateToDelete = recurringSession.getSessionDate();
        uniqueStudentList.deleteSessionInRecurringSession(AMY, INDEX_FIRST_SESSION, sessionDateToDelete);

        assertEquals(AMY.getListOfSessions(), BOB.getListOfSessions());
    }

    @Test
    public void removeSessionInRecurringSession_validRemoveSessionAtSessionEnd() {
        uniqueStudentList.add(AMY);
        uniqueStudentList.add(BOB);
        AMY.getListOfSessions().clear();
        BOB.getListOfSessions().clear();
        RecurringSession recurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_RECURRING_START_DATE_AMY, VALID_TIME)
                        .withLastSessionDate(VALID_RECURRING_END_DATE_AMY, VALID_TIME).build();
        RecurringSession anotherRecurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_RECURRING_START_DATE_AMY, VALID_TIME)
                        .withLastSessionDate(VALID_RECURRING_END_DATE_AMY, VALID_TIME).build();
        anotherRecurringSession = anotherRecurringSession.withLastSessionDate(anotherRecurringSession
                .getLastSessionDate().minusDays(7));
        AMY.addSession(recurringSession);
        BOB.addSession(anotherRecurringSession);
        SessionDate sessionDateToDelete = recurringSession.getLastSessionDate();
        uniqueStudentList.deleteSessionInRecurringSession(AMY, INDEX_FIRST_SESSION, sessionDateToDelete);

        assertEquals(AMY.getListOfSessions(), BOB.getListOfSessions());
    }

    @Test
    public void removeSessionInRecurringSession_validRemoveMiddleSession() {
        uniqueStudentList.add(AMY);
        uniqueStudentList.add(BOB);
        AMY.getListOfSessions().clear();
        BOB.getListOfSessions().clear();
        SessionDate sessionDateToDelete = new SessionDate("2021-04-22", VALID_TIME);
        RecurringSession recurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_RECURRING_START_DATE_AMY, VALID_TIME)
                        .withLastSessionDate(VALID_RECURRING_END_DATE_AMY, VALID_TIME).build();
        RecurringSession firstHalfRecurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_RECURRING_START_DATE_AMY, VALID_TIME)
                        .withLastSessionDate(sessionDateToDelete.minusDays(7).getDate().toString(), VALID_TIME).build();
        RecurringSession secondHalfRecurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(sessionDateToDelete.addDays(7).getDate().toString(), VALID_TIME)
                        .withLastSessionDate(VALID_RECURRING_END_DATE_AMY, VALID_TIME).build();
        AMY.addSession(recurringSession);
        BOB.addSessions(firstHalfRecurringSession, secondHalfRecurringSession);
        uniqueStudentList.deleteSessionInRecurringSession(AMY, INDEX_FIRST_SESSION, sessionDateToDelete);

        assertEquals(AMY.getListOfSessions(), BOB.getListOfSessions());
    }

    @Test
    public void removeSessionInRecurringSession_validRemoveSingleDayRecurringSession() {
        uniqueStudentList.add(AMY);
        uniqueStudentList.add(BOB);
        AMY.getListOfSessions().clear();
        BOB.getListOfSessions().clear();
        RecurringSession recurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_RECURRING_START_DATE_AMY, VALID_TIME)
                        .withLastSessionDate(VALID_RECURRING_START_DATE_AMY, VALID_TIME).build();
        AMY.addSession(recurringSession);
        SessionDate sessionDateToDelete = recurringSession.getSessionDate();
        uniqueStudentList.deleteSessionInRecurringSession(AMY, INDEX_FIRST_SESSION, sessionDateToDelete);

        assertEquals(AMY.getListOfSessions(), BOB.getListOfSessions());
    }

    @Test
    public void removeSessionInRecurringSession_validFirstHalfNonRecurring() {
        uniqueStudentList.add(AMY);
        uniqueStudentList.add(BOB);
        AMY.getListOfSessions().clear();
        BOB.getListOfSessions().clear();
        RecurringSession recurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_RECURRING_START_DATE_AMY, VALID_TIME)
                        .withLastSessionDate(VALID_RECURRING_END_DATE_AMY, VALID_TIME).build();
        AMY.addSession(recurringSession);
        Session firstHalfSession =
                new SessionBuilder()
                        .withSessionDate(VALID_RECURRING_START_DATE_AMY, VALID_TIME).build();
        RecurringSession secondHalfRecurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(recurringSession.getSessionDate().addDays(14).getDate().toString(), VALID_TIME)
                        .withLastSessionDate(VALID_RECURRING_END_DATE_AMY, VALID_TIME).build();
        SessionDate sessionDateToDelete = recurringSession.getSessionDate().addDays(7);
        BOB.addSessions(firstHalfSession, secondHalfRecurringSession);
        uniqueStudentList.deleteSessionInRecurringSession(AMY, INDEX_FIRST_SESSION, sessionDateToDelete);

        assertEquals(AMY.getListOfSessions(), BOB.getListOfSessions());
    }

    @Test
    public void hasSession_sessionExists_returnsTrue() {
        Session session = new SessionBuilder().build();
        Student student = ALICE;
        student.addSession(session);
        uniqueStudentList.add(student);
        assertTrue(uniqueStudentList.hasSession(session));
    }

    @Test
    public void hasSession_sessionDoesNotExist_returnsFalse() {
        Session session = new SessionBuilder().build();
        Session newSession = new SessionBuilder().withSessionDate("2022-01-01", "00:00").build();
        ALICE.addSession(session);
        uniqueStudentList.add(ALICE);
        assertFalse(uniqueStudentList.hasSession(newSession));
    }
}
