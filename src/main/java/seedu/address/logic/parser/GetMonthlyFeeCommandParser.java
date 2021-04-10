package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.stream.Stream;

import seedu.address.logic.commands.GetMonthlyFeeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Year;
import seedu.address.model.fee.Month;
import seedu.address.model.student.Name;

/**
 * Parses input arguments and creates a new GetMonthlyFeeCommand object
 */
public class GetMonthlyFeeCommandParser implements Parser<GetMonthlyFeeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GetMonthlyFeeCommand
     * and returns a GetMonthlyFeeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GetMonthlyFeeCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MONTH, PREFIX_YEAR);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MONTH, PREFIX_YEAR)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetMonthlyFeeCommand.MESSAGE_USAGE));
        }

        Name studentName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Month month = ParserUtil.parseMonth(argMultimap.getValue(PREFIX_MONTH).get());
        Year year = ParserUtil.parseYear(argMultimap.getValue(PREFIX_YEAR).get());

        return new GetMonthlyFeeCommand(studentName, month, year);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
