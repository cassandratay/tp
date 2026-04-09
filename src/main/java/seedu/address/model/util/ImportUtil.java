package seedu.address.model.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for parsing person data from CSV files.
 */
public class ImportUtil {

    /**
     * Reads a CSV file and returns its data rows as string arrays, excluding the header row.
     * Malformed rows with too few columns are skipped.
     *
     * @param filePath The path to the CSV file to read.
     * @return A list of parsed CSV rows.
     * @throws IOException If the file cannot be read or a row has unmatched quotes.
     */
    public static List<String[]> parseCsv(String filePath) throws IOException {
        List<String[]> rowsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // skip header
                    continue;
                }

                String[] row = parseCsvRow(line);
                stripFields(row);

                if (row.length < 9) {
                    continue; // skip invalid rows
                }
                rowsList.add(row);
            }
        }

        return rowsList;
    }

    /**
     * Parses a single CSV row, supporting quoted fields and escaped quotes.
     *
     * @param line The CSV row to parse.
     * @return The parsed fields in the row.
     * @throws IOException If the row contains unmatched quotes.
     */
    private static String[] parseCsvRow(String line) throws IOException {
        List<String> fields = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);

            if (currentChar == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    currentField.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (currentChar == ',' && !inQuotes) {
                fields.add(currentField.toString());
                currentField.setLength(0);
            } else {
                currentField.append(currentChar);
            }
        }

        if (inQuotes) {
            throw new IOException("Malformed CSV row: unmatched quote");
        }

        fields.add(currentField.toString());
        return fields.toArray(new String[0]);
    }

    private static void stripFields(String[] row) {
        for (int i = 0; i < row.length; i++) {
            row[i] = row[i].trim();
        }
    }
}
