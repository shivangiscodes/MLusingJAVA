package shivangi;

import weka.classifiers.lazy.IBk;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.classifiers.Evaluation;

import java.io.File;

public class knn {
    public static void main(String[] args) throws Exception {
        // Load CSV file
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File("C:\\Users\\Shivam\\OneDrive\\Desktop\\CCopy\\src\\main\\resources\\data1.csv"));
        Instances data = loader.getDataSet();
        data.setClassIndex(data.numAttributes() - 1); // Assuming the class attribute is the last one

        // Initialize and build the classifier
        IBk knn = new IBk();
        knn.setKNN(3); // Set the number of neighbors (k) for KNN
        knn.buildClassifier(data);

        // Evaluate the model
        Evaluation eval = new Evaluation(data);
        eval.evaluateModel(knn, data);

        // Print the evaluation results
        System.out.println(eval.toSummaryString());

        // Print the accuracy for each instance
        for (int i = 0; i < data.numInstances(); i++) {
            double actual = data.instance(i).classValue();
            double predicted = knn.classifyInstance(data.instance(i));
            System.out.println("Instance " + (i + 1) + ": Actual=" + actual + ", Predicted=" + predicted);
        }

        // Calculate and print overall accuracy
        double accuracy = eval.pctCorrect();
        System.out.printf("Overall Accuracy: %.2f%%\n", accuracy);
    }
}
