package seedu.address.model.person;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BoxTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Box(null, null));
    }

    @Test
    public void constructor_invalidBoxName_throwsIllegalArgumentException() {
        String invalidBoxName = "";
        ExpiryDate validExpiryDate = new ExpiryDate("2026-12-31");
        assertThrows(IllegalArgumentException.class, () -> new Box(invalidBoxName, validExpiryDate));
    }

    @Test
    public void constructor_invalidExpiryDate_throwsIllegalArgumentException() {
        String validBoxName = "box-1";
        String invalidExpiryDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Box(validBoxName, new ExpiryDate(invalidExpiryDate)));
    }

    @Test
    public void isValidBoxName() {
        // null box name
        assertThrows(NullPointerException.class, () -> Box.isValidBoxName(null));
    }
}
