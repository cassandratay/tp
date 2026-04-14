package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.person.Box;
import seedu.address.model.person.Person;

public class SampleDataUtilTest {

    @Test
    public void getBoxSet_validInputs_trimsAndSortsBoxes() {
        Set<Box> boxes = SampleDataUtil.getBoxSet(" box-2 : 3 ", "box-1:2");

        assertEquals(2, boxes.size());
        List<Box> orderedBoxes = List.copyOf(boxes);
        assertEquals("box-1", orderedBoxes.get(0).getBoxName());
        assertEquals("box-2", orderedBoxes.get(1).getBoxName());
    }

    @Test
    public void getBoxSet_emptyInput_returnsEmptySet() {
        assertEquals(Set.of(), SampleDataUtil.getBoxSet());
    }

    @Test
    public void getBoxSet_missingSeparator_throwsIllegalArgumentException() {
        IllegalArgumentException thrown =
                assertThrows(IllegalArgumentException.class, () -> SampleDataUtil.getBoxSet("box-1"));

        assertEquals(Box.MESSAGE_INVALID_BOX_WITH_EXPIRY_FORMAT, thrown.getMessage());
    }

    @Test
    public void getBoxSet_separatorAtStartOrEnd_throwsIllegalArgumentException() {
        IllegalArgumentException leading =
                assertThrows(IllegalArgumentException.class, () -> SampleDataUtil.getBoxSet(":2"));
        IllegalArgumentException trailing =
                assertThrows(IllegalArgumentException.class, () -> SampleDataUtil.getBoxSet("box-1:"));

        assertEquals(Box.MESSAGE_INVALID_BOX_WITH_EXPIRY_FORMAT, leading.getMessage());
        assertEquals(Box.MESSAGE_INVALID_BOX_WITH_EXPIRY_FORMAT, trailing.getMessage());
    }

    @Test
    public void getBoxSet_invalidBoxName_throwsIllegalArgumentException() {
        IllegalArgumentException thrown =
                assertThrows(IllegalArgumentException.class, () -> SampleDataUtil.getBoxSet("Box-1:2"));

        assertEquals(Box.MESSAGE_CONSTRAINTS, thrown.getMessage());
    }

    @Test
    public void getBoxSet_invalidNumOfMonths_throwsIllegalArgumentException() {
        IllegalArgumentException thrown =
                assertThrows(IllegalArgumentException.class, () -> SampleDataUtil.getBoxSet("box-1:0"));

        assertEquals(ParserUtil.MESSAGE_INVALID_NUM_OF_MONTHS, thrown.getMessage());
    }

    @Test
    public void getSamplePersons_returnsExpectedNumberOfPersons() {
        Person[] samplePersons = SampleDataUtil.getSamplePersons();

        assertEquals(6, samplePersons.length);
        assertEquals("Alex Yeoh", samplePersons[0].getName().fullName);
    }

    @Test
    public void getSampleAddressBook_containsAllSamplePersons() {
        AddressBook sampleAddressBook = (AddressBook) SampleDataUtil.getSampleAddressBook();

        assertEquals(6, sampleAddressBook.getPersonList().size());
    }
}
