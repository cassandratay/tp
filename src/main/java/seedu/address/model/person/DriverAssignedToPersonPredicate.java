package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.delivery.DeliveryAssignmentHashMap;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given., placeholder
 */
public class DriverAssignedToPersonPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public DriverAssignedToPersonPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(DeliveryAssignmentHashMap.getInstance()
                .getDriverForPerson(person).getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DriverAssignedToPersonPredicate)) {
            return false;
        }

        DriverAssignedToPersonPredicate otherDriverAssignedToPersonPredicate = (DriverAssignedToPersonPredicate) other;
        return keywords.equals(otherDriverAssignedToPersonPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
