package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tuition.exceptions.DuplicateTuitionException;
import seedu.address.model.student.exceptions.StudentNotFoundException;
import seedu.address.model.tuition.UniqueTuitionList;
import seedu.address.testutil.StudentBuilder;

public class UniqueTuitionListTest {

    private final UniqueTuitionList uniqueTuitionList = new UniqueTuitionList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTuitionList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueTuitionList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueTuitionList.add(ALICE);
        assertTrue(uniqueTuitionList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTuitionList.add(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .build();
        assertTrue(uniqueTuitionList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTuitionList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueTuitionList.add(ALICE);
        assertThrows(DuplicateTuitionException.class, () -> uniqueTuitionList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTuitionList.setStudent(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTuitionList.setStudent(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(StudentNotFoundException.class, () -> uniqueTuitionList.setStudent(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueTuitionList.add(ALICE);
        uniqueTuitionList.setStudent(ALICE, ALICE);
        UniqueTuitionList expectedUniqueTuitionList = new UniqueTuitionList();
        expectedUniqueTuitionList.add(ALICE);
        assertEquals(expectedUniqueTuitionList, uniqueTuitionList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueTuitionList.add(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .build();
        uniqueTuitionList.setStudent(ALICE, editedAlice);
        UniqueTuitionList expectedUniqueTuitionList = new UniqueTuitionList();
        expectedUniqueTuitionList.add(editedAlice);
        assertEquals(expectedUniqueTuitionList, uniqueTuitionList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueTuitionList.add(ALICE);
        uniqueTuitionList.setStudent(ALICE, BOB);
        UniqueTuitionList expectedUniqueTuitionList = new UniqueTuitionList();
        expectedUniqueTuitionList.add(BOB);
        assertEquals(expectedUniqueTuitionList, uniqueTuitionList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueTuitionList.add(ALICE);
        uniqueTuitionList.add(BOB);
        assertThrows(DuplicateTuitionException.class, () -> uniqueTuitionList.setStudent(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTuitionList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(StudentNotFoundException.class, () -> uniqueTuitionList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueTuitionList.add(ALICE);
        uniqueTuitionList.remove(ALICE);
        UniqueTuitionList expectedUniqueTuitionList = new UniqueTuitionList();
        assertEquals(expectedUniqueTuitionList, uniqueTuitionList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTuitionList.setStudents((UniqueTuitionList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueTuitionList.add(ALICE);
        UniqueTuitionList expectedUniqueTuitionList = new UniqueTuitionList();
        expectedUniqueTuitionList.add(BOB);
        uniqueTuitionList.setStudents(expectedUniqueTuitionList);
        assertEquals(expectedUniqueTuitionList, uniqueTuitionList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTuitionList.setStudents((List<Student>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueTuitionList.add(ALICE);
        List<Student> studentList = Collections.singletonList(BOB);
        uniqueTuitionList.setStudents(studentList);
        UniqueTuitionList expectedUniqueTuitionList = new UniqueTuitionList();
        expectedUniqueTuitionList.add(BOB);
        assertEquals(expectedUniqueTuitionList, uniqueTuitionList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Student> listWithDuplicateStudents = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateTuitionException.class, () -> uniqueTuitionList.setStudents(listWithDuplicateStudents));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTuitionList.asUnmodifiableObservableList().remove(0));
    }
}
