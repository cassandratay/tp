package seedu.address.model.delivery.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.model.commons.name.Name;
import seedu.address.model.commons.phone.Phone;
import seedu.address.model.delivery.DeliveryAssignmentHashMap;
import seedu.address.model.delivery.Driver;
import seedu.address.testutil.PersonBuilder;

public class ExportUtilTest {

    private static final Driver DRIVER = new Driver(new Name("o'connor"), new Phone("91234567"));

    @TempDir
    Path tempDir;

    @BeforeEach
    public void setUp() {
        DeliveryAssignmentHashMap.clearAssignments();
    }

    @AfterEach
    public void tearDown() {
        DeliveryAssignmentHashMap.clearAssignments();
    }

    @Test
    public void exportAssignmentsAsHtml_writesExpectedHtmlContent() throws IOException {
        Path outputFile = tempDir.resolve("export.html");
        DeliveryAssignmentHashMap map = DeliveryAssignmentHashMap.getInstance();
        map.assign(DRIVER, new PersonBuilder()
                .withName("amy o'brien")
                .withEmail("amy.o+test@example.com")
                .withAddress("1 <Main & Co> \"Road\" O'Brien, Singapore 123456")
                .withDeliveryStatus("pending")
                .build());
        map.assign(DRIVER, new PersonBuilder()
                .withName("bob packed")
                .withEmail("bob.packed@example.com")
                .withAddress("2 Plain Street, Singapore 234567")
                .withDeliveryStatus("packed")
                .build());
        map.assign(DRIVER, new PersonBuilder()
                .withName("carol delivered")
                .withEmail("carol.delivered@example.com")
                .withAddress("3 Delivery Road, Singapore 345678")
                .withDeliveryStatus("delivered")
                .withBoxes("box-1:2", "box-2:3")
                .build());

        ExportUtil.exportAssignmentsAsHtml(map, outputFile.toString());

        String html = Files.readString(outputFile);
        assertTrue(html.contains("<!DOCTYPE html>"));
        assertTrue(html.contains("<title>Delivery Assignments</title>"));
        assertTrue(html.contains("<h1>Delivery Assignments</h1>"));
        assertTrue(html.contains("<p class=\"timestamp\">Generated on: "));
        assertTrue(html.contains(
                "<th>Status</th><th>Name</th><th>Phone</th><th>Email</th><th>Address</th><th>Boxes</th>"));
        assertTrue(html.contains("<caption>Driver: O&#x27;CONNOR - 91234567</caption>"));
        assertTrue(html.contains("Amy O&#x27;Brien"));
        assertTrue(html.contains("1 &lt;Main &amp; Co&gt; &quot;Road&quot; O&#x27;Brien, Singapore 123456"));
        assertTrue(html.contains("<ol>"));
        assertTrue(html.contains("<li>box-1 (expires: "));
        assertTrue(html.contains("<li>box-2 (expires: "));

        int pendingIndex = html.indexOf("class=\"status-pending\">Pending</td>");
        int packedIndex = html.indexOf("class=\"status-packed\">Packed</td>");
        int deliveredIndex = html.indexOf("class=\"status-delivered\">Delivered</td>");
        assertTrue(pendingIndex >= 0);
        assertTrue(packedIndex >= 0);
        assertTrue(deliveredIndex >= 0);
        assertTrue(pendingIndex < packedIndex);
        assertTrue(packedIndex < deliveredIndex);
    }

    @Test
    public void exportAssignmentsAsHtml_withNoAssignedDrivers_writesDocumentWithoutDriverTables() throws IOException {
        Path outputFile = tempDir.resolve("empty-export.html");
        DeliveryAssignmentHashMap map = DeliveryAssignmentHashMap.getInstance();

        ExportUtil.exportAssignmentsAsHtml(map, outputFile.toString());

        String html = Files.readString(outputFile);
        assertTrue(html.contains("<!DOCTYPE html>"));
        assertTrue(html.contains("<h1>Delivery Assignments</h1>"));
        assertTrue(html.contains("<p class=\"timestamp\">Generated on: "));
        assertFalse(html.contains("<caption>Driver: "));
    }
}
