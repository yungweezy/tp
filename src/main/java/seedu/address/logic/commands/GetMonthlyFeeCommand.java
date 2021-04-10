package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.time.LocalDateTime;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.DateUtil;
import seedu.address.commons.util.FeeUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Year;
import seedu.address.model.fee.Month;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;

/**
 * Gets the monthly fee of a particular student on a specific month and year.
 */
public class GetMonthlyFeeCommand extends Command {
    public static final String COMMAND_WORD = "fee";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the monthly fee of a student in a "
        + "particular month and year"
        + "\nParameters: "
        + PREFIX_NAME + "STUDENT NAME "
        + PREFIX_MONTH + "MONTH "
        + PREFIX_YEAR + "YEAR "
        + "\nExample: " + COMMAND_WORD + " "
        + PREFIX_NAME + "John Doe "
        + PREFIX_MONTH + "1 "
        + PREFIX_YEAR + "2021 ";

    private Name studentName;
    private Month month;
    private Year year;

    /**
     * Creates an GetMonthlyFeeCommand to get the specified monthly fee
     */
    public GetMonthlyFeeCommand(Name studentName, Month month, Year year) {
        requireAllNonNull(studentName, month, year);
        this.studentName = studentName;
        this.month = month;
        this.year = year;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasName(studentName)) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_NAME);
        }

        Student student = model.getStudentWithName(studentName);
        LocalDateTime currMonthYear;
        LocalDateTime nextMonthYear;

        currMonthYear = DateUtil.getFirstDayOfMonth(month, year);
        nextMonthYear = currMonthYear.plusMonths(1);

        // Get month fee for this month for that particular student
        double monthlyFee = FeeUtil.getFeePerStudent(student, currMonthYear, nextMonthYear);

        return new CommandResult(String.format("Monthly fee for %s on %s, %s is $%.2f",
            studentName.toString(), month.getMonthName(), year.toString(), monthlyFee));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof GetMonthlyFeeCommand // instanceof handles nulls
            && studentName.equals(((GetMonthlyFeeCommand) other).studentName)
            && month.equals(((GetMonthlyFeeCommand) other).month)
            && year.equals(((GetMonthlyFeeCommand) other).year));
    }
}
