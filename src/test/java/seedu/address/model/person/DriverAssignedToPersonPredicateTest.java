package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.commons.name.Name;
import seedu.address.model.commons.phone.Phone;
import seedu.address.model.delivery.DeliveryAssignmentHashMap;
import seedu.address.model.delivery.Driver;
import seedu.address.testutil.PersonBuilder;

public class DriverAssignedToPersonPredicateTest {

    @BeforeEach
    public void setUp() {
        DeliveryAssignmentHashMap.clearAssignments();
    }

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Alex");
        List<String> secondPredicateKeywordList = Arrays.asList("Alex", "Jordan");

        DriverAssignedToPersonPredicate firstPredicate =
                new DriverAssignedToPersonPredicate(firstPredicateKeywordList);
        DriverAssignedToPersonPredicate secondPredicate =
                new DriverAssignedToPersonPredicate(secondPredicateKeywordList);

        assertTrue(firstPredicate.equals(firstPredicate));

        DriverAssignedToPersonPredicate firstPredicateCopy =
                new DriverAssignedToPersonPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        assertFalse(firstPredicate.equals(1));
        assertFalse(firstPredicate.equals(null));
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_driverContainsKeywords_returnsTrue() {
        Person assignedPerson = new PersonBuilder().withName("Alice Bob").build();
        Driver alex = new Driver(new Name("Alex Tan"), new Phone("91234567"));
        DeliveryAssignmentHashMap.getInstance().assign(alex, assignedPerson);

        DriverAssignedToPersonPredicate predicate =
                new DriverAssignedToPersonPredicate(Collections.singletonList("Alex"));
        assertTrue(predicate.test(assignedPerson));

        predicate = new DriverAssignedToPersonPredicate(Arrays.asList("Alex", "Jordan"));
        assertTrue(predicate.test(assignedPerson));

        predicate = new DriverAssignedToPersonPredicate(Collections.singletonList("aLeX"));
        assertTrue(predicate.test(assignedPerson));
    }

    @Test
    public void test_unassignedPerson_returnsFalse() {
        DriverAssignedToPersonPredicate predicate =
                new DriverAssignedToPersonPredicate(Collections.singletonList("Alex"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        DriverAssignedToPersonPredicate predicate = new DriverAssignedToPersonPredicate(keywords);

        String expected = DriverAssignedToPersonPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
