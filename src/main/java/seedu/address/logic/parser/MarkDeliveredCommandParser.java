package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkDeliveredCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkDeliveredCommand object
 */
public class MarkDeliveredCommandParser implements Parser<MarkDeliveredCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkDeliveredCommand
     * and returns a MarkDeliveredCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkDeliveredCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MarkDeliveredCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkDeliveredCommand.MESSAGE_USAGE), pe);
        }
    }

}
