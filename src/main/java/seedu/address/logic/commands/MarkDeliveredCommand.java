package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Marks a person as "delivered" using it's displayed index from the address book.
 */
public class MarkDeliveredCommand extends Command {

    public static final String NOT_IMPLEMENTED_YET = "markDelivered command not implemented yet";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";

    public static final String COMMAND_WORD = "markdelivered";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the delivery status of the person "
            + "identified by the index number used in the "
            + "last person listing to delivered.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_DELIVERED_SUCCESS = "Package successfully delivered to: %1$s";

    public final Index targetIndex;

    public MarkDeliveredCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToMarkDelivered = lastShownList.get(targetIndex.getZeroBased());
        // personToMarkDelivered.markDelivered() // NOTE: hook up command by implementing markDelivered in Person.java

        return new CommandResult(
                String.format(MESSAGE_MARK_DELIVERED_SUCCESS, Messages.format(personToMarkDelivered))
        );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkDeliveredCommand)) {
            return false;
        }

        MarkDeliveredCommand otherMarkDeliveredCommand = (MarkDeliveredCommand) other;
        return targetIndex.equals(otherMarkDeliveredCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
