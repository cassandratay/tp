package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Box;
import seedu.address.model.person.ExpiryDate;

/**
 * Jackson-friendly version of {@link Box}
 */
public class JsonAdaptedBox {

    private final String boxName;
    private final String expiryDate;

    /**
     * Constructs a {@code JsonAdaptedBox} with the given {@code boxName}.
     */
    @JsonCreator
    public JsonAdaptedBox(@JsonProperty("boxName") String boxName,
                          @JsonProperty("expiryDate") String expiryDate) {
        this.boxName = boxName;
        this.expiryDate = expiryDate;
    }

    /**
     * Converts a given {@code Box} into this class for Jackson use.
     */
    public JsonAdaptedBox(Box source) {
        boxName = source.boxName;
        expiryDate = source.expiryDate.value;
    }

    public String getBoxName() {
        return boxName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * Converts this Jackson-friendly adapted box object into the model's {@code Box} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted box.
     */
    public Box toModelType() throws IllegalValueException {
        if (!Box.isValidBoxName(boxName)) {
            throw new IllegalValueException(Box.MESSAGE_CONSTRAINTS);
        }
        if (!ExpiryDate.isValidExpiryDate(expiryDate)) {
            throw new IllegalValueException(ExpiryDate.MESSAGE_CONSTRAINTS);
        }
        return new Box(boxName, new ExpiryDate(expiryDate));
    }
}
