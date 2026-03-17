package seedu.address.model.person;

/**
 * Represents a Person's delivery status in the address book.
 * Guarantees: immutable; can be constructed from valid strings via {@link #fromString(String)}
 */
public enum DeliveryStatus {
    PENDING("Pending"),
    PREPARING("Preparing"),
    DELIVERED("Delivered");

    public static final String MESSAGE_CONSTRAINTS =
            "Delivery status should be one of the following: 'pending', 'preparing', 'delivered'";

    public final String deliveryStatus;

    /**
     * Constructs a {@code DeliveryStatus}.
     *
     * @param deliveryStatus A valid delivery status.
     */
    DeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public String toString() {
        return deliveryStatus;
    }

    public static DeliveryStatus fromString(String deliveryStatus) {
        try {
            return DeliveryStatus.valueOf(deliveryStatus.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }
}
