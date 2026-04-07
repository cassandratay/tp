package seedu.address.logic.parser;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@link ImportCommand} object.
 * <p>
 * The user must provide only a CSV file name (no directories) located in the
 * {@code data/} folder. Files must end with {@code .csv}. Providing a path,
        * directories, empty file names, or invalid file types will result in a
 * {@link ParseException}.
        * <p>
 * Any {@link seedu.address.logic.commands.exceptions.CommandException} thrown
 * by {@link ImportCommand} due to invalid file names is wrapped as a
 * {@link ParseException} to ensure consistent parser error handling.
        */
public class ImportCommandParser implements Parser<ImportCommand> {

    @Override
    public ImportCommand parse(String args) throws ParseException {
        String trimmed = args.trim();
        if (trimmed.isEmpty()) {
            throw new ParseException("File name cannot be empty.");
        }

        try {
            return new ImportCommand(trimmed);
        } catch (CommandException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }
}
