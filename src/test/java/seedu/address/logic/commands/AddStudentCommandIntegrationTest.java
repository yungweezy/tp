package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTuition.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tuition.Tuition;
import seedu.address.testutil.TuitionBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddTuitionCommand}.
 */
public class AddStudentCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Tuition validTuition = new TuitionBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addTuition(validTuition);

        assertCommandSuccess(new AddStudentCommand(validTuition), model,
                String.format(AddStudentCommand.MESSAGE_SUCCESS, validTuition), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Tuition tuitionInList = model.getAddressBook().getTuitionList().get(0);
        assertCommandFailure(new AddStudentCommand(tuitionInList), model, AddStudentCommand.MESSAGE_DUPLICATE_STUDENT);
    }

}
