import java.util.List;
import java.io.FileNotFoundException;
import java.util.Arrays;



public class kNNMain{

  public static void main(String... args) throws FileNotFoundException{

    // TASK 1: Use command line arguments to point DataSet.readDataSet method to
    // the desired file. Choose a given DataPoint, and print its features and label
    List<DataPoint> dataSet = DataSet.readDataSet(args[0]);

    DataPoint point =  dataSet.get(0);

    System.out.print(point.getLabel());
    
    String x = Arrays.toString(point.getX());
    System.out.println("\t" + x);


    //TASK 2:Use the DataSet class to split the dataSetSet into Training and Held Out Test Dataset
    List<DataPoint> testSet = DataSet.getTestSet(dataSet, 0.8);
    List<DataPoint> trainSet = DataSet.getTrainingSet(dataSet, 0.2);


    // TASK 4: write a new method in DataSet.java which takes as arguments to DataPoint objects,
    // and returns the Euclidean distance between those two points (as a double)



    // TASK 5: Use the KNNClassifier class to determine the k nearest neighbors to a given DataPoint,
    // and make a print a predicted target label



    // TASK 6: loop over the datapoints in the held out test set, and make predictions for Each
    // point based on nearest neighbors in training set. Calculate accuracy of model.


  }

}
