package seedu.address.logic.parser;

import seedu.address.logic.commands.AddBoxCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Box;
import seedu.address.model.person.ExpiryDate;
import seedu.address.model.person.Name;

import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class AddBoxCommandParser implements Parser<AddBoxCommand> {

    public AddBoxCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_BOX, PREFIX_EXPIRY_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_BOX, PREFIX_EXPIRY_DATE)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBoxCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_EXPIRY_DATE);

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        ExpiryDate expiryDate = ParserUtil.parseExpiryDate(argMultimap.getValue(PREFIX_EXPIRY_DATE).get());
        Set<Box> boxesToAdd = ParserUtil.parseBoxes(argMultimap.getAllValues(PREFIX_BOX), expiryDate);

        return new AddBoxCommand(name, boxesToAdd);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
