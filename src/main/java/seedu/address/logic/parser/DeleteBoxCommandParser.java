package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteBoxCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

public class DeleteBoxCommandParser implements Parser<DeleteBoxCommand> {

    public DeleteBoxCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_BOX);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_BOX)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBoxCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME);

        Name targetName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Set<String> targetBoxNames = new HashSet<>();
        for (String box: argMultimap.getAllValues(PREFIX_BOX)) {
            targetBoxNames.add(ParserUtil.parseBoxName(box));
        }

        return new DeleteBoxCommand(targetName, targetBoxNames);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
