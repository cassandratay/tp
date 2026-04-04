package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandAssistantTest {

    private final CommandAssistant commandAssistant = new CommandAssistant();

    @Test
    public void getSuggestion_emptyInput_returnsEmptySuggestion() {
        assertEquals("", commandAssistant.getSuggestion(""));
    }

    @Test
    public void getSuggestion_addCommand_showsAllRequiredFields() {
        assertEquals(" n/NAME p/PHONE e/EMAIL a/ADDRESS b/BOX ex/EXPIRY_DATE [o/REMARKS] [t/TAG]...",
                commandAssistant.getSuggestion("add"));
    }

    @Test
    public void getSuggestion_addCommandWithStartedFields_showsRemainingFields() {
        assertEquals(" p/PHONE e/EMAIL a/ADDRESS b/BOX ex/EXPIRY_DATE [o/REMARKS] [t/TAG]...",
                commandAssistant.getSuggestion("add n/John Doe"));
    }

    @Test
    public void getSuggestion_assignCommandWithMissingPhone_showsNextRequiredField() {
        assertEquals(" p/PHONE [n/NAME p/PHONE]...", commandAssistant.getSuggestion("assign n/Alex"));
    }

    @Test
    public void getSuggestion_editCommandAfterIndex_showsEditableFields() {
        assertEquals(" [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [o/REMARKS] [ex/EXPIRY_DATE] [t/TAG]...",
                commandAssistant.getSuggestion("edit 1"));
    }

    @Test
    public void getSuggestion_deleteCommand_showsAcceptedTargets() {
        assertEquals(" INDEX|EMAIL", commandAssistant.getSuggestion("delete"));
    }

    @Test
    public void getSuggestion_uniquePartialCommand_completesCommandWordAndTemplate() {
        assertEquals("mark INDEX REMARK", commandAssistant.getSuggestion("re"));
    }

    @Test
    public void getSuggestion_ambiguousPartialCommand_returnsEmptySuggestion() {
        assertEquals("", commandAssistant.getSuggestion("a"));
    }
}
