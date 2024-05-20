package shivangi;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class CSVStats {

    public static void main(String[] args) throws IOException {
        // Path to your CSV file
        String csvFile = "C:\\Users\\Shivam\\OneDrive\\Desktop\\C\\src\\main\\resources\\data1.csv";

        // Read CSV file
        Reader reader = new FileReader(csvFile);
        CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

        // Get column headers
        String[] headers = csvParser.getHeaderMap().keySet().toArray(new String[0]);

        // Initialize DescriptiveStatistics for each column
        DescriptiveStatistics[] columnStats = new DescriptiveStatistics[headers.length];
        for (int i = 0; i < headers.length; i++) {
            columnStats[i] = new DescriptiveStatistics();
        }

        // Iterate through CSV records
        for (CSVRecord record : csvParser) {
            for (int i = 0; i < headers.length; i++) {
                double value = Double.parseDouble(record.get(headers[i]));
                columnStats[i].addValue(value);
            }
        }

        // Print statistics for each column
        for (int i = 0; i < headers.length; i++) {
            System.out.println("Statistics for column '" + headers[i] + "':");
            System.out.println("Mean: " + columnStats[i].getMean());
            System.out.println("Median: " + columnStats[i].getPercentile(50));
            System.out.println("Standard Deviation: " + columnStats[i].getStandardDeviation());
            System.out.println("Minimum: " + columnStats[i].getMin());
            System.out.println("Maximum: " + columnStats[i].getMax());
            System.out.println();
        }

        // Close resources
        csvParser.close();
    }
}

