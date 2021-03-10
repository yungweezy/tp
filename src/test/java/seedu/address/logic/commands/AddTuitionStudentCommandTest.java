package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.tuition.Tuition;
import seedu.address.testutil.TuitionBuilder;

public class AddTuitionStudentCommandTest {

    @Test
    public void constructor_nullTuition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTuitionStudentCommand(null));
    }

    @Test
    public void execute_tuitionAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTuitionAdded modelStub = new ModelStubAcceptingTuitionAdded();
        Tuition validTuition = new TuitionBuilder().build();

        CommandResult commandResult = new AddTuitionStudentCommand(validTuition.getStudent()).execute(modelStub);

        assertEquals(String.format(AddTuitionStudentCommand.MESSAGE_SUCCESS, validTuition),
            commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTuition), modelStub.tuitionAdded);
    }

    @Test
    public void execute_duplicateTuition_throwsCommandException() {
        Tuition validTuition = new TuitionBuilder().build();
        AddTuitionStudentCommand addTuitionStudentCommand = new AddTuitionStudentCommand(validTuition.getStudent());
        ModelStub modelStub = new ModelStubWithTuition(validTuition);

        assertThrows(CommandException.class, AddTuitionStudentCommand.MESSAGE_DUPLICATE_STUDENT, () ->
            addTuitionStudentCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Tuition alice = new TuitionBuilder().withName("Alice").build();
        Tuition bob = new TuitionBuilder().withName("Bob").build();
        AddTuitionStudentCommand addAliceCommand = new AddTuitionStudentCommand(alice.getStudent());
        AddTuitionStudentCommand addBobCommand = new AddTuitionStudentCommand(bob.getStudent());

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddTuitionStudentCommand addAliceCommandCopy = new AddTuitionStudentCommand(alice.getStudent());
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different tuition -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTuition(Tuition tuition) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTuition(Tuition tuition) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTuition(Tuition tuition) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTuition(Tuition tuition, Tuition editedTuition) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Tuition> getFilteredTuitionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTuitionList(Predicate<Tuition> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single tuition.
     */
    private class ModelStubWithTuition extends ModelStub {
        private final Tuition tuition;

        ModelStubWithTuition(Tuition tuition) {
            requireNonNull(tuition);
            this.tuition = tuition;
        }

        @Override
        public boolean hasTuition(Tuition tuition) {
            requireNonNull(tuition);
            return this.tuition.isSameTuition(tuition);
        }
    }

    /**
     * A Model stub that always accept the tuition being added.
     */
    private class ModelStubAcceptingTuitionAdded extends ModelStub {
        final ArrayList<Tuition> tuitionAdded = new ArrayList<>();

        @Override
        public boolean hasTuition(Tuition tuition) {
            requireNonNull(tuition);
            return tuitionAdded.stream().anyMatch(tuition::isSameTuition);
        }

        @Override
        public void addTuition(Tuition tuition) {
            requireNonNull(tuition);
            tuitionAdded.add(tuition);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
