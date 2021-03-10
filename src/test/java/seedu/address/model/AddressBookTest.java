package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTuition.ALICE;
import static seedu.address.testutil.TypicalTuition.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tuition.Tuition;
import seedu.address.model.tuition.exceptions.DuplicateTuitionException;
import seedu.address.testutil.TuitionBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getTuitionList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two tuition with the same identity fields
        Tuition editedAlice = new TuitionBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .build();
        List<Tuition> newTuition = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newTuition);

        assertThrows(DuplicateTuitionException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTuition(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTuition(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addTuition(ALICE);
        assertTrue(addressBook.hasTuition(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addTuition(ALICE);
        Tuition editedAlice = new TuitionBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .build();
        assertTrue(addressBook.hasTuition(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTuitionList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose tuition list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Tuition> tuition = FXCollections.observableArrayList();

        AddressBookStub(Collection<Tuition> tuition) {
            this.tuition.setAll(tuition);
        }

        @Override
        public ObservableList<Tuition> getTuitionList() {
            return tuition;
        }
    }

}
