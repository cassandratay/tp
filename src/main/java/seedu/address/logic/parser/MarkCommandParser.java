package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DeliveryStatus;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkCommandParser implements Parser<MarkCommand> {

    private static final Pattern MARK_ARGS_FORMAT = Pattern.compile("(?<index>\\S+)(?<newStatus>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the MarkCommand
     * and returns a MarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkCommand parse(String args) throws ParseException {
        final Matcher matcher = MARK_ARGS_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(matcher.group("index"));
        DeliveryStatus newStatus = ParserUtil.parseDeliveryStatus(matcher.group("newStatus").trim());
        return new MarkCommand(index, newStatus);
    }
}
