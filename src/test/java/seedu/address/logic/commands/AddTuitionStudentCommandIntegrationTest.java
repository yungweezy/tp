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
public class AddTuitionStudentCommandIntegrationTest {

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

        assertCommandSuccess(new AddTuitionStudentCommand(validTuition.getStudent()), model,
                String.format(AddTuitionStudentCommand.MESSAGE_SUCCESS, validTuition), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Tuition tuitionInList = model.getAddressBook().getTuitionList().get(0);
        assertCommandFailure(new AddTuitionStudentCommand(tuitionInList.getStudent()), model,
            AddTuitionStudentCommand.MESSAGE_DUPLICATE_STUDENT);
    }

}
