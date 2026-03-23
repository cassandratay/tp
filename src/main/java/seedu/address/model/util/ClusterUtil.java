package seedu.address.model.util;

import static java.util.regex.Pattern.compile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.model.person.Person;

/**
 * Utility class for clustering Persons based on their postal code prefixes.
 */
public class ClusterUtil {
    private static final Pattern POSTAL_CODE_PATTERN = compile("\\b\\d{6}\\b");

    /**
     * Extracts the postal code from the given address string.
     *
     * @param address The address string to extract the postal code from.
     * @return The postal code as a string.
     * @throws IllegalArgumentException if the postal code is not found in the address.
     */
    public static String getPostalCodeStringFromAddress(String address) {
        Matcher postalCodeMatcher = POSTAL_CODE_PATTERN.matcher(address);
        postalCodeMatcher.find();
        return postalCodeMatcher.group();
    }

    /**
     * Extracts the postal code from the given address string and converts it to an integer.
     *
     * @param address The address string to extract the postal code from.
     * @return The postal code as an integer.
     * @throws IllegalArgumentException if the postal code is not found in the address or if it cannot be parsed as an integer.
     */
    public static int getPostalCodeIntegerFromAddress(String address) {
        return Integer.parseInt(getPostalCodeStringFromAddress(address));
    }

    /**
     * Extracts the postal code prefix (first two digits) from the given address string and converts it to an integer.
     *
     * @param address The address string to extract the postal code prefix from.
     * @return The postal code prefix as an integer.
     * @throws IllegalArgumentException if the postal code is not found in the address or if the prefix cannot be parsed as an integer.
     */
    public static int getPostalPrefixFromAddress(String address) {
        String postalCode = getPostalCodeStringFromAddress(address);
        return Integer.parseInt(postalCode.substring(0, 2));
    }

    /**
     * Extracts the postal code prefix (first two digits) from the given Person and converts it to an integer.
     *
     * @param person The Person whose address is used to extract the postal code prefix.
     * @return The postal code prefix as an integer.
     * @throws IllegalArgumentException if the postal code is not found in the person's address or if the prefix cannot be parsed as an integer.
     */
    public static int getPostalPrefixFromPerson(Person person) {
        return getPostalPrefixFromAddress(person.getAddress().value);
    }
}
