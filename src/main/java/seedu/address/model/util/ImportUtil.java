package seedu.address.model.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for importing subscribers from CSV.
 */
public class ImportUtil {

    /**
     * Reads CSV file and returns list of rows (as String arrays), skipping the header.
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

                String[] row = line.split(",", -1);
                stripFields(row);

                if (row.length < 9) {
                    continue; // skip invalid rows
                }
                rowsList.add(row);
            }
        }

        return rowsList;
    }

    private static void stripFields(String[] row) {
        for (int i = 0; i < row.length; i++) {
            row[i] = row[i].trim();
            if (row[i].startsWith("\"") && row[i].endsWith("\"")) {
                row[i] = row[i].substring(1, row[i].length() - 1);
            }
        }
    }
}
