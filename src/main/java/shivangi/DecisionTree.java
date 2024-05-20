package shivangi;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.classifiers.Evaluation;

import java.io.File;

public class DecisionTree {
    public static void main(String[] args) throws Exception {
        // Load CSV file
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File("C:\\Users\\Shivam\\OneDrive\\Desktop\\CCopy\\src\\main\\resources\\data1.csv"));
        Instances data = loader.getDataSet();
        data.setClassIndex(data.numAttributes() - 1); // Assuming the class attribute is the last one

        // Convert numeric class attribute to nominal
        NumericToNominal converter = new NumericToNominal();
        converter.setAttributeIndices("last");
        converter.setInputFormat(data);
        data = weka.filters.Filter.useFilter(data, converter);

        // Initialize and build the classifier (J48 decision tree)
        J48 tree = new J48();
        tree.buildClassifier(data);

        // Evaluate the model
        Evaluation eval = new Evaluation(data);
        eval.evaluateModel(tree, data);

        // Print the evaluation results
        System.out.println(eval.toSummaryString());

        // Print the class predictions and calculate accuracy
        System.out.println("Predictions:");
        int correctCount = 0;
        for (int i = 0; i < data.numInstances(); i++) {
            double actual = data.instance(i).classValue();
            String actualClass = data.classAttribute().value((int) actual);
            double predicted = tree.classifyInstance(data.instance(i));
            String predictedClass = data.classAttribute().value((int) predicted);

            System.out.println("Instance " + (i + 1) + ": Actual=" + actualClass + ", Predicted=" + predictedClass);
            
            // Count correct predictions
            if (actualClass.equals(predictedClass)) {
                correctCount++;
            }
        }

        // Print accuracy
        double accuracy = (double) correctCount / data.numInstances() * 100;
        System.out.println("Accuracy: " + accuracy + "%");
    }
}
