package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.session.RecurringSession;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionDate;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;

/**
 * Deletes a session from a recurring session identified using it's displayed index from the address book, date, and
 * time of the session to be deleted. This command splits a recurring session into two recurring sessions into an
 * earlier recurring session (if any) and a later recurring session (if any).
 */
public class DeleteRecurringSessionCommand extends Command {

    public static final String COMMAND_WORD = "delete_rec_session";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a session in a recurring session identified by an index number used in the student's"
            + " list of sessions and the date of the session\n"
            + "Parameters: n/FULLNAME i/INDEX d/DATE \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "John Doe " + PREFIX_INDEX + "1 "
            + PREFIX_DATE + "2021-03-30";

    public static final String MESSAGE_DELETE_SESSION_OF_RECURRING_SESSION_SUCCESS =
            "Deleted session on %2$s of Recurring Session: %1$s";

    private final Name studentName;

    private final Index targetIndex;

    private SessionDate sessionDate;

    /**
     * Creates an DeleteRecurringSessionCommand
     */
    public DeleteRecurringSessionCommand(Name studentName, Index targetIndex, SessionDate sessionDate) {
        requireAllNonNull(studentName, targetIndex, sessionDate);
        this.studentName = studentName;
        this.targetIndex = targetIndex;
        this.sessionDate = sessionDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasName(studentName)) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_NAME);
        }
        Student student = model.getStudentWithName(studentName);

        assert student != null : "student should not be null!";

        if (targetIndex.getOneBased() > student.getListOfSessions().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
        }

        Session sessionToDelete = student.getListOfSessions().get(targetIndex.getZeroBased());

        if (!(sessionToDelete instanceof RecurringSession)) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECURRING_SESSION_INDEX);
        }
        sessionDate = new SessionDate(sessionDate.getDate().toString(),
                sessionToDelete.getSessionDate().getTime().toString());
        RecurringSession recurringSessionToDelete = (RecurringSession) sessionToDelete;
        if (!recurringSessionToDelete.hasSessionOnDate(sessionDate)) {
            throw new CommandException(Messages.MESSAGE_INVALID_DATE_OF_RECURRING_SESSION);
        }

        if (!recurringSessionToDelete.getSessionDate().isSameTime(sessionDate)) {
            throw new CommandException(Messages.MESSAGE_INVALID_TIME_OF_RECURRING_SESSION);
        }


        model.deleteSessionInRecurringSession(studentName, targetIndex, sessionDate);
        return new CommandResult(String.format(MESSAGE_DELETE_SESSION_OF_RECURRING_SESSION_SUCCESS,
                sessionToDelete.toString(), sessionDate.getDate().toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteRecurringSessionCommand // instanceof handles nulls
                && studentName.equals(((DeleteRecurringSessionCommand) other).studentName)
                && targetIndex.equals(((DeleteRecurringSessionCommand) other).targetIndex))
                && sessionDate.equals(((DeleteRecurringSessionCommand) other).sessionDate); // state check
    }
}
