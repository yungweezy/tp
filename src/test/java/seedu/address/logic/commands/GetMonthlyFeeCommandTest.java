package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.CARL;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.FeeUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.Year;
import seedu.address.model.fee.Month;
import seedu.address.model.student.Name;

public class GetMonthlyFeeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_unknownStudent_throwsCommandException() {
        GetMonthlyFeeCommand getMonthlyFeeCommand = new GetMonthlyFeeCommand(new Name("Random Name"),
            new Month(2), new Year(2021));
        assertCommandFailure(getMonthlyFeeCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_NAME);
    }

    // Alice has 4 session and has only 2 session that is on 1 Jan 2021
    // The other 2 session, one has a different month, one has a different year
    @Test
    public void execute_correctMonthlyFee_success1() throws CommandException {
        GetMonthlyFeeCommand getMonthlyFeeCommand = new GetMonthlyFeeCommand(ALICE.getName(),
            new Month(2), new Year(2021));
        CommandResult commandResult = getMonthlyFeeCommand.execute(model);

        LocalDateTime startPeriod = LocalDateTime.of(2021, 2, 1, 0, 0);
        LocalDateTime endPeriod = LocalDateTime.of(2021, 3, 1, 0, 0);

        double expectedFee = FeeUtil.getFeePerStudent(ALICE, startPeriod, endPeriod);

        assertEquals(String.format("Monthly fee for %s on %s, %s is $%.2f", ALICE.getName(),
            new Month(2).getMonthName(), new Year(2021).toString(), expectedFee),
            commandResult.getFeedbackToUser());
    }

    // CARL has 3 recurring session and has only 2 of them happens on March.
    @Test
    public void execute_correctMonthlyFee_success2() throws CommandException {
        GetMonthlyFeeCommand getMonthlyFeeCommand = new GetMonthlyFeeCommand(CARL.getName(),
            new Month(3), new Year(2021));
        CommandResult commandResult = getMonthlyFeeCommand.execute(model);

        LocalDateTime startPeriod = LocalDateTime.of(2021, 3, 1, 0, 0);
        LocalDateTime endPeriod = LocalDateTime.of(2021, 4, 1, 0, 0);

        double expectedFee = FeeUtil.getFeePerStudent(CARL, startPeriod, endPeriod);

        assertEquals(String.format("Monthly fee for %s on %s, %s is $%.2f", CARL.getName(),
            new Month(3).getMonthName(), new Year(2021).toString(), expectedFee),
            commandResult.getFeedbackToUser());
    }
}
