package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRIVER;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DriverAssignedToPersonPredicate;
import seedu.address.model.person.PersonHasBoxPredicate;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(" " + trimmedArgs, PREFIX_DRIVER);
        List<String> driverKeywords = argMultimap.getAllValues(PREFIX_DRIVER);

        if (!driverKeywords.isEmpty()) {
            if (!argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
            }

            List<String> splitKeywords = driverKeywords.stream()
                    .flatMap(k -> Arrays.stream(k.trim().split("\\s+")))
                    .filter(k -> !k.isEmpty())
                    .collect(Collectors.toList());

            if (splitKeywords.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
            }

            return new FilterCommand(new DriverAssignedToPersonPredicate(splitKeywords));
        }

        String[] boxKeywords = trimmedArgs.split("\\s+");

        return new FilterCommand(new PersonHasBoxPredicate(Arrays.asList(boxKeywords)));
    }

}
