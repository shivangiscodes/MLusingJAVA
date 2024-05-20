package shivangi;

import weka.classifiers.functions.SMO;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.classifiers.Evaluation;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;

import java.io.File;

public class SVM {
    public static void main(String[] args) throws Exception {
        // Load CSV file
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File("C:\\Users\\Shivam\\OneDrive\\Desktop\\CCopy\\src\\main\\resources\\data1.csv"));
        Instances data = loader.getDataSet();

        // Assuming the class attribute is the last one
        data.setClassIndex(data.numAttributes() - 1);

        // Convert the class attribute to nominal
        NumericToNominal convert = new NumericToNominal();
        String[] options = new String[]{"-R", Integer.toString(data.classIndex() + 1)};
        convert.setOptions(options);
        convert.setInputFormat(data);
        Instances newData = Filter.useFilter(data, convert);

        // Initialize and build the classifier
        SMO svm = new SMO();
        svm.buildClassifier(newData);

        // Evaluate the model
        Evaluation eval = new Evaluation(newData);
        eval.evaluateModel(svm, newData);

        // Print the evaluation results
        System.out.println(eval.toSummaryString());

        // Print the accuracy for each instance
        for (int i = 0; i < newData.numInstances(); i++) {
            double actual = newData.instance(i).classValue();
            double predicted = svm.classifyInstance(newData.instance(i));
            System.out.println("Instance " + (i + 1) + ": Actual=" + actual + ", Predicted=" + predicted);
        }

        // Calculate and print overall accuracy
        double accuracy = eval.pctCorrect();
        System.out.printf("Overall Accuracy: %.2f%%\n", accuracy);
    }
}
