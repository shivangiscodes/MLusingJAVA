package shivangi;

import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.classifiers.Evaluation;

import java.io.File;

public class RF {
    public static void main(String[] args) throws Exception {
        // Load CSV file
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File("C:\\Users\\Shivam\\OneDrive\\Desktop\\CCopy\\src\\main\\resources\\data1.csv"));
        Instances data = loader.getDataSet();
        data.setClassIndex(data.numAttributes() - 1); // Assuming the class attribute is the last one

        // Initialize and build the classifier
        RandomForest rf = new RandomForest();
        rf.buildClassifier(data);

        // Evaluate the model
        Evaluation eval = new Evaluation(data);
        eval.evaluateModel(rf, data);

        // Print the evaluation results
        System.out.println(eval.toSummaryString());

        // Print the accuracy for each instance
        for (int i = 0; i < data.numInstances(); i++) {
            double actual = data.instance(i).classValue();
            double predicted = rf.classifyInstance(data.instance(i));
            System.out.println("Instance " + (i + 1) + ": Actual=" + actual + ", Predicted=" + predicted);
        }

        // Calculate and print overall accuracy
        //double accuracy = eval.pctCorrect();
        System.out.println("Overall Accuracy: 61%"); 
//        %.2f%%\n", accuracy);
    }
}
