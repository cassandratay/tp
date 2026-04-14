package seedu.address.logic.commands.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.commons.name.Name;
import seedu.address.model.commons.phone.Phone;
import seedu.address.model.delivery.Driver;
import seedu.address.model.person.Address;
import seedu.address.model.person.Box;
import seedu.address.model.person.DeliveryStatus;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

public class ClearDriversUtilTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void clearDriverAssignments_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ClearDriversUtil.clearDriverAssignments(null));
    }

    @Test
    public void clearDriverAssignments_emptyAddressBook_noException() {
        Model emptyModel = new ModelManager(new AddressBook(), new UserPrefs());

        assertDoesNotThrow(() -> ClearDriversUtil.clearDriverAssignments(emptyModel));
        assertEquals(0, emptyModel.getAddressBook().getPersonList().size());
    }

    @Test
    public void clearDriverAssignments_personsWithDrivers_success() {
        List<Person> originalPersons = List.copyOf(model.getAddressBook().getPersonList());

        Driver driver = new Driver(new Name("Kyle"), new Phone("91234567"));

        for (Person originalPerson : originalPersons) {
            Person personWithDriver = createPersonWithDriver(originalPerson, driver);
            model.setPerson(originalPerson, personWithDriver);
        }

        ClearDriversUtil.clearDriverAssignments(model);

        List<Person> clearedPersons = model.getAddressBook().getPersonList();
        assertEquals(originalPersons, clearedPersons);
    }

    @Test
    public void clearDriverAssignments_personsWithoutDrivers_noChange() {
        List<Person> originalPersons = List.copyOf(model.getAddressBook().getPersonList());

        ClearDriversUtil.clearDriverAssignments(model);

        assertEquals(originalPersons, model.getAddressBook().getPersonList());
    }

    private Person createPersonWithDriver(Person personToCopy, Driver driver) {
        Name nameCopy = personToCopy.getName();
        Phone phoneCopy = personToCopy.getPhone();
        Email emailCopy = personToCopy.getEmail();
        Address addressCopy = personToCopy.getAddress();
        DeliveryStatus statusCopy = personToCopy.getDeliveryStatus();
        Set<Box> boxesCopy = new HashSet<>(personToCopy.getBoxes());
        Remark remarkCopy = personToCopy.getRemark();
        Set<Tag> tagsCopy = new HashSet<>(personToCopy.getTags());

        return new Person(nameCopy, phoneCopy, emailCopy, addressCopy,
                boxesCopy, remarkCopy, statusCopy, tagsCopy, driver);
    }
}
