package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.DriverAssignedToPersonPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonHasBoxPredicate;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
public class FilterCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        PersonHasBoxPredicate firstPredicate =
                new PersonHasBoxPredicate(Collections.singletonList("box-1"));
        PersonHasBoxPredicate secondPredicate =
                new PersonHasBoxPredicate(Collections.singletonList("box-2"));

        FilterCommand filterFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondPredicate);

        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        FilterCommand filterFirstCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        assertFalse(filterFirstCommand.equals(1));
        assertFalse(filterFirstCommand.equals(null));
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_zeroMatches_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasBoxPredicate predicate = preparePredicate("box-999");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 5);
        PersonHasBoxPredicate predicate = preparePredicate("box-1 box-2");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_driverKeyword_multiplePersonsFound() {
        Person assignedAlice = new PersonBuilder(ALICE).withDriver("Alex Tan", "91234567").build();
        Person assignedCarl = new PersonBuilder(CARL).withDriver("Alex Tan", "91234567").build();
        model.setPerson(ALICE, assignedAlice);
        model.setPerson(CARL, assignedCarl);
        expectedModel.setPerson(ALICE, assignedAlice);
        expectedModel.setPerson(CARL, assignedCarl);

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        DriverAssignedToPersonPredicate predicate =
                new DriverAssignedToPersonPredicate(Collections.singletonList("Alex"));
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(assignedAlice, assignedCarl), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        PersonHasBoxPredicate predicate = new PersonHasBoxPredicate(Arrays.asList("box-1"));
        FilterCommand filterCommand = new FilterCommand(predicate);
        String expected = FilterCommand.class.getCanonicalName() + "{boxPredicate=" + predicate + "}";
        assertEquals(expected, filterCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code PersonHasBoxPredicate}.
     */
    private PersonHasBoxPredicate preparePredicate(String userInput) {
        return new PersonHasBoxPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
