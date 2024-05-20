package shivangi;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVVisualizer {

    public static void main(String[] args) throws IOException {
        // Path to your CSV file
        String csvFile = "C:\\Users\\Shivam\\OneDrive\\Desktop\\C\\src\\main\\resources\\data1.csv";

        // Read CSV file
        Reader reader = new FileReader(csvFile);
        CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

        // List to store values of the first column
        List<Double> values = new ArrayList<>();

        // Iterate through CSV records and add values of the first column to the list
        for (CSVRecord record : csvParser) {
            double value = Double.parseDouble(record.get(0)); // Assuming the first column contains numerical data
            values.add(value);
        }

        // Convert list to array
        double[] data = new double[values.size()];
        for (int i = 0; i < values.size(); i++) {
            data[i] = values.get(i);
        }

        // Create a dataset for the histogram
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Histogram", data, 10); // 10 bins

        // Create the histogram chart
        JFreeChart chart = ChartFactory.createHistogram(
                "Histogram",
                "Value",
                "Frequency",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Create a panel to display the chart
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        // Display the chart in a JFrame
        JFrame frame = new JFrame("Histogram Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);

        // Close resources
        csvParser.close();
    }
}
