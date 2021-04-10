package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteRecurringSessionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.session.SessionDate;
import seedu.address.model.student.Name;

/**
 * Parses input arguments and creates a new DeleteRecurringSessionCommand object
 */
public class DeleteRecurringSessionCommandParser implements Parser<DeleteRecurringSessionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteRecurringSessionCommand
     * and returns a DeleteRecurringSessionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteRecurringSessionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_INDEX, PREFIX_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_INDEX, PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteRecurringSessionCommand.MESSAGE_USAGE));
        }
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
        SessionDate sessionDate = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATE).get(),
                "00:00");
        return new DeleteRecurringSessionCommand(name, index, sessionDate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
