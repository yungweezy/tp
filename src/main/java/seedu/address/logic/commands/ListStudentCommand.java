package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Lists all tuition students in the address book to the user.
 */
public class ListStudentCommand extends Command {

    public static final String COMMAND_WORD = "list_student";

    public static final String MESSAGE_SUCCESS = "Listed all current students";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTuitionList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}