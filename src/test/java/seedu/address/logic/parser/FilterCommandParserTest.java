package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.person.DriverAssignedToPersonPredicate;
import seedu.address.model.person.PersonHasBoxPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        FilterCommand expectedFilterCommand =
                new FilterCommand(new PersonHasBoxPredicate(Arrays.asList("box-1", "box-2")));
        assertParseSuccess(parser, "box-1 box-2", expectedFilterCommand);
        assertParseSuccess(parser, " \n box-1 \n \t box-2  \t", expectedFilterCommand);
    }

    @Test
    public void parse_driverArgs_returnsFilterCommand() {
        FilterCommand expectedFilterCommand =
                new FilterCommand(new DriverAssignedToPersonPredicate(Arrays.asList("Alex", "Jordan")));
        assertParseSuccess(parser, "d/Alex d/Jordan", expectedFilterCommand);
        assertParseSuccess(parser, " \n d/Alex \n \t d/Jordan  \t", expectedFilterCommand);
    }

    @Test
    public void parse_mixedBoxAndDriverArgs_throwsParseException() {
        assertParseFailure(parser, "box-1 d/Alex",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }
}
