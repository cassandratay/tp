package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's delivery status in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeliveryStatus(String)}
 */
public class DeliveryStatus {

    public static final String MESSAGE_CONSTRAINTS =
            "Delivery status should be one of the following: 'pending', 'preparing', 'out-for-delivery', 'delivered'";

    /*
     * Allowed delivery status values.
     */
    public static final String VALIDATION_REGEX = "pending|preparing|out-for-delivery|delivered";

    public final String deliveryStatus;

    /**
     * Constructs a {@code DeliveryStatus}.
     *
     * @param status A valid delivery status.
     */
    public DeliveryStatus(String status) {
        requireNonNull(status);
        checkArgument(isValidDeliveryStatus(status), MESSAGE_CONSTRAINTS);
        deliveryStatus = status;
    }

    /**
     * Returns true if a given string is a valid delivery status.
     */
    public static boolean isValidDeliveryStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return deliveryStatus;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeliveryStatus)) {
            return false;
        }

        DeliveryStatus otherStatus = (DeliveryStatus) other;
        return deliveryStatus.equals(otherStatus.deliveryStatus);
    }

    @Override
    public int hashCode() {
        return deliveryStatus.hashCode();
    }

}
