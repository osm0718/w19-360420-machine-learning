import java.util.List;
import java.io.FileNotFoundException;
import java.util.Arrays;



public class kNNMain{

	public static void main(String... args) throws FileNotFoundException
	{

		// TASK 1: Use command line arguments to point DataSet.readDataSet method to
		// the desired file. Choose a given DataPoint, and print its features and label
		List<DataPoint> dataSet = DataSet.readDataSet(args[0]);

		DataPoint point = dataSet.get(0);

		System.out.print(point.getLabel());
    
		String x = Arrays.toString(point.getX());
		System.out.println("\t" + x);


		//TASK 2:Use the DataSet class to split the dataSetSet into Training and Held Out Test Dataset
		List<DataPoint> testSet = DataSet.getTestSet(dataSet, 0.3);
		List<DataPoint> trainSet = DataSet.getTrainingSet(dataSet, 0.7);


		// TASK 4: write a new method in DataSet.java which takes as arguments to DataPoint objects,
		// and returns the Euclidean distance between those two points (as a double)

		// TASK 5: Use the KNNClassifier class to determine the k nearest neighbors to a given DataPoint,
		// and print a predicted target label
		KNNClassifier knn = new KNNClassifier(3);

		String prediction = knn.predict(dataSet, point);

		System.out.println(prediction);


		// TASK 6: loop over the datapoints in the held out test set, and make predictions for Each
		// point based on nearest neighbors in training set. Calculate accuracy of model.
		DataPoint testPredict;
		double[] accuracy = new double[1000];
		double[] precision = new double[1000];
		double[] recall = new double[1000];
		double[] f1 = new double[1000];
    
		for (int j=0;j<accuracy.length;j++)
		{
			double accuracyCtr = 0;
			double tp = 0;
			double fp = 0;
			double fn = 0;

			dataSet = DataSet.readDataSet(args[0]);
			testSet = DataSet.getTrainingSet(dataSet, 0.3);
			trainSet = DataSet.getTrainingSet(dataSet, 0.7);

        
			for (int i=1; i<testSet.size();i++)
			{
				testPredict = testSet.get(i);
            
				String guess = knn.predict(trainSet, testPredict);

				// Accuracy
				if(guess.equals(testPredict.getLabel()))
					accuracyCtr++;
				
				// Benign = positive, malignant = negative
				// True positive
				if (guess.equals("benign") && testPredict.getLabel().equals("benign"))
					tp++;
				// False positive
				if (guess.equals("benign") && testPredict.getLabel().equals("malignant"))
					fp++;
				// False negative
				if (guess.equals("malignant") && testPredict.getLabel().equals("benign"))
					fn++;

			}

			accuracy[j] = accuracyCtr / testSet.size() * 100;
			precision[j] = tp / (tp + fp);
			recall[j] = tp / (tp + fn);

			System.out.println("Generation " + (j+1) + " : " + accuracy[j]);
		}

		double meanAccuracy = mean(accuracy);
		double stdevAccuracy = standardDeviation(accuracy);

		double meanPrecision = mean(precision);
		double stdevPrecision = standardDeviation(precision);

		double meanRecall = mean(recall);
		double stdevRecall = standardDeviation(recall);

		System.out.println("-----------------------------------\n");
		System.out.println("Results over 1000 generation: ");
		System.out.println();
		System.out.println("Average accuracy: " + meanAccuracy);
		System.out.println("StDev accuracy:" + stdevAccuracy);
		System.out.println();
		System.out.println("Average precision:" + meanPrecision);
		System.out.println("StDev precision:" + stdevPrecision);
		System.out.println();
		System.out.println("Average recall:" + meanRecall);
		System.out.println("StDev recall:" + stdevRecall);
		System.out.println();
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
