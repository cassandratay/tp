package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.BOX_DESC_BOX1;
import static seedu.address.logic.commands.CommandTestUtil.BOX_NAME_DESC_BOX1;
import static seedu.address.logic.commands.CommandTestUtil.EXPIRY_DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXPIRY_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOX_NAME_BOX2;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditBoxCommand;
import seedu.address.logic.commands.EditBoxCommand.EditBoxDescriptor;
import seedu.address.model.commons.name.Name;
import seedu.address.model.person.Box;
import seedu.address.model.person.ExpiryDate;
import seedu.address.testutil.EditBoxDescriptorBuilder;

public class EditBoxCommandParserTest {

    private static final String NEW_BOX_DESC_BOX2 = " nb/" + VALID_BOX_NAME_BOX2;
    private static final String INVALID_NEW_BOX = " nb/box2";

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBoxCommand.MESSAGE_USAGE);

    private EditBoxCommandParser parser = new EditBoxCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        Name targetName = new Name("Amy Bee");
        String targetBoxName = "box-1";
        String userInput = PREAMBLE_WHITESPACE + NAME_DESC_AMY + BOX_NAME_DESC_BOX1 + NEW_BOX_DESC_BOX2
                + EXPIRY_DATE_DESC_AMY;

        EditBoxDescriptor descriptor = new EditBoxDescriptorBuilder()
                .withBoxName(VALID_BOX_NAME_BOX2)
                .withExpiryDate("2026-12-31")
                .build();
        EditBoxCommand expectedCommand = new EditBoxCommand(targetName, targetBoxName, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_onlyBoxNameSpecified_success() {
        Name targetName = new Name("Amy Bee");
        String targetBoxName = "box-1";
        String userInput = PREAMBLE_WHITESPACE + NAME_DESC_AMY + BOX_NAME_DESC_BOX1 + NEW_BOX_DESC_BOX2;

        EditBoxDescriptor descriptor = new EditBoxDescriptorBuilder()
                .withBoxName(VALID_BOX_NAME_BOX2)
                .build();
        EditBoxCommand expectedCommand = new EditBoxCommand(targetName, targetBoxName, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_onlyExpiryDateSpecified_success() {
        Name targetName = new Name("Amy Bee");
        String targetBoxName = "box-1";
        String userInput = PREAMBLE_WHITESPACE + NAME_DESC_AMY + BOX_NAME_DESC_BOX1 + EXPIRY_DATE_DESC_AMY;

        EditBoxDescriptor descriptor = new EditBoxDescriptorBuilder()
                .withExpiryDate("2026-12-31")
                .build();
        EditBoxCommand expectedCommand = new EditBoxCommand(targetName, targetBoxName, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidNewBoxName_failure() {
        String userInput = PREAMBLE_WHITESPACE + NAME_DESC_AMY + BOX_NAME_DESC_BOX1 + INVALID_NEW_BOX;
        assertParseFailure(parser, userInput, Box.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidNewExpiryDate_failure() {
        String userInput = PREAMBLE_WHITESPACE + NAME_DESC_AMY + BOX_NAME_DESC_BOX1 + INVALID_EXPIRY_DATE_DESC;
        assertParseFailure(parser, userInput, ExpiryDate.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_missingCompulsoryPrefixes_failure() {
        assertParseFailure(parser, NEW_BOX_DESC_BOX2, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, BOX_DESC_BOX1, MESSAGE_INVALID_FORMAT);
    }
}
