import java.util.List;
import java.io.FileNotFoundException;
import java.util.Arrays;



public class kNNMain{

  public static void main(String... args) throws FileNotFoundException{

    // TASK 1: Use command line arguments to point DataSet.readDataSet method to
    // the desired file. Choose a given DataPoint, and print its features and label
    List<DataPoint> dataSet = DataSet.readDataSet(args[0]);

    DataPoint point = dataSet.get(0);

    System.out.print(point.getLabel());
    
    String x = Arrays.toString(point.getX());
    System.out.println("\t" + x);


    //TASK 2:Use the DataSet class to split the dataSetSet into Training and Held Out Test Dataset
    List<DataPoint> testSet = DataSet.getTestSet(dataSet, 0.2);
    List<DataPoint> trainSet = DataSet.getTrainingSet(dataSet, 0.8);


    // TASK 4: write a new method in DataSet.java which takes as arguments to DataPoint objects,
    // and returns the Euclidean distance between those two points (as a double)


    // TASK 5: Use the KNNClassifier class to determine the k nearest neighbors to a given DataPoint,
    // and print a predicted target label
    KNNClassifier knn = new KNNClassifier(5);

    String prediction = knn.predict(dataSet, point);

    System.out.println(prediction);


    // TASK 6: loop over the datapoints in the held out test set, and make predictions for Each
    // point based on nearest neighbors in training set. Calculate accuracy of model.
    DataPoint testPredict = dataSet.get(0);
    double ctr = 0.;
    double[] accuracy = new double[1000];
    
    for (int j=0;j<1000;j++)
    {
        ctr = 0;
        List<DataPoint> trainer = DataSet.getTrainingSet(dataSet, 0.8);

        
        for (int i=0; i<trainer.size();i++)
        {
            testPredict = trainer.get(i);
            
            String guess = knn.predict(trainer, testPredict);

            if(guess.equals(testPredict.getLabel()))
                ctr++;
        }
        
        accuracy[j] = ctr / trainer.size() * 100;    

        System.out.println("Generation " + (j+1) + " : " + accuracy[j]);
    }
    
    double sum = 0;
    for(int k=0;k<1000;k++)
    {
        sum += accuracy[k];
    }

    double meanAccuracy = mean(accuracy);
    double stdevAccuracy = standardDeviation(accuracy);

    System.out.println("-----------------------------------\n");
    System.out.println("Results over 1000 generation: ");
    System.out.println("Average accuracy: " + meanAccuracy);
    System.out.println("Standard deviation:" + stdevAccuracy);

  }


  public static double mean(double[] arr){
    /*
    Method that takes as an argument an array of doubles
    Returns: average of the elements of array, as a double
    */
    double sum = 0.0;

    for (double a : arr){
      sum += a;
    }
    return (double)sum/arr.length;
  }

  public static double standardDeviation(double[] arr){
    /*
    Method that takes as an argument an array of doubles
    Returns: standard deviation of the elements of array, as a double
    Dependencies: requires the *mean* method written above
    */
    double avg = mean(arr);
    double sum = 0.0;
    for (double a : arr){
      sum += Math.pow(a-avg,2);
    }
    return (double)sum/arr.length;
  }

}
