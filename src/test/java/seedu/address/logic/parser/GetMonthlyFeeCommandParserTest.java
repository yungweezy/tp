package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudents.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GetMonthlyFeeCommand;
import seedu.address.model.Year;
import seedu.address.model.fee.Month;

public class GetMonthlyFeeCommandParserTest {

    private GetMonthlyFeeCommandParser parser = new GetMonthlyFeeCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            GetMonthlyFeeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsMonthlyFeeCommand() {
        // no leading and trailing whitespaces
        GetMonthlyFeeCommand expectedGetMonthlyFeeCommand =
                new GetMonthlyFeeCommand(ALICE.getName(), new Month(1), new Year(2021));
        assertParseSuccess(parser, String.format(" n/%s m/1 y/2021", ALICE.getName().toString()),
            expectedGetMonthlyFeeCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, String.format(" n/%s  m/1  y/2021", ALICE.getName().toString()),
            expectedGetMonthlyFeeCommand);
    }

}
