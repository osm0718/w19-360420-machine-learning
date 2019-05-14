# Error Analysis
## 360-420-DW, Section 01
## Oliver Miller and Brendan Patience

## Distributions of Model Accuracy
Your accuracy changes every time because, whenever you call the getTrainingSet and getTestSet methods from DataSet.java, it shuffles your full data set, creating new test and training sets with every iteration. 

When comparing our model's performance, we should look at the actual results of the initial data set. If the frequency of the predicted labels is very close to the frequency of the real labels, then our model's performance could be very accurate, asssuming there are no other sources of error.

## Analysis of different error types
After 1000 generations and k=3, our results yielded accuracies of 97% on average and had standard deviations around 0.8. This means that k nearest neighbors is a very effective model at predicting results based on a given set of data and a full data set.

A prediction is described as "false" (either positive or negative) when the prediction claims one thing and, in reality, it is the opposite. So, a false positive is when the prediction guesses that the result is positive, but the actual answer is negative. A false negative is when the prediction guesses that the result is negative, but the actual answer is positive. 

Recall is a ratio of correct positive guesses to the total number of actual positive results, whereas precision is a ratio of correct positive guesses to the total number positive guesses (whether they were correct or not). A good baseline would be a value of 1 for both parameters. If either recall or precision are equal to 1, then this means that the model's predictions would all be correct. For our model, we received values of around 0.98 for recall and precision, with very small standard deviations for both. This indicates that, for the most part, our positive guesses were neither false positives or false negatives. 

The hyperparameter k is a number that inidicates how many neighbors we want to look at for each prediction. For example, if k=3, then we will look at the 3 nearest neighbors to our test point. Whichever target dominates the nearest neighbors dictates the target prediction. As k increases, the results become less and less precise. This is because, the more neighbors that are brought into question, the farther away from the test point we go to search for a new neighbor. The further we are from the test point, the less likely we are to find target values that are consistent with those closest to the test point. For example, if we were to increase k to a value of 9, accuracy would decrease by 2%. If we were to go even further and make k=15, accuracy would decrease another 1%. Thus, a larger k leads to a larger chance of an inaccurate model.
