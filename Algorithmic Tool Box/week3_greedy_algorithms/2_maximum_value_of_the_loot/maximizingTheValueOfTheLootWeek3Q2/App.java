package maximizingTheValueOfTheLootWeek3Q2;

import java.util.Scanner;

public class App 
{

	
	public static void main(String[] args) 
	{
		
		Scanner keyboard = new Scanner(System.in);
		int numberOfItems, weightOfKnapsack = 0;
		System.out.println("Please enter the number of items :");
		numberOfItems = keyboard.nextInt();
		
		System.out.println("Please enter the Weight of a knapsack");
		weightOfKnapsack = keyboard.nextInt();
		
		double[] values = new double[numberOfItems];
		double[] weights = new double[numberOfItems];
		
		for(int i = 0; i < numberOfItems; i++)
		{
			System.out.println("Please enter the value of item " + (i+1) + " :");
			values[i]  = keyboard.nextDouble();
			
			System.out.println("Please enter the weight of item " + (i+1) + " :");
			weights[i]  = keyboard.nextDouble();
		}
		
		String formattedAns = String.format("%.4f", knapsackMaximumValue(weightOfKnapsack, values, weights));
		System.out.println(formattedAns);
		
	}
	
	public static double knapsackMaximumValue( double knapsackWeight, double[] values, double[] weights)
	{
		if(knapsackWeight == 0 || weights.length ==0)
		{
			return 0;
		}
		int maxValuePerWeightIndex = 0;
		
		for(int i = 1; i < weights.length; i++)
		{
			if(values[i]/weights[i] > values[maxValuePerWeightIndex]/ weights[maxValuePerWeightIndex])
				maxValuePerWeightIndex = i;
		}
		
		double totalValue = 0;
		
		double amount = 0;
		amount = Math.min(knapsackWeight, weights[maxValuePerWeightIndex]);
		totalValue += amount * values[maxValuePerWeightIndex]/ weights[maxValuePerWeightIndex]; 
		
		double[] newValues = new double[values.length-1];
		double[] newWeights = new double[weights.length -1];
		int index = 0;
		for(int i = 0; i < weights.length ; i++)
		{
			if(i!= maxValuePerWeightIndex)
			{
				newValues[index] = values[i];
				newWeights[index] = weights[i];
				index++;
			}
		}
		
		return totalValue + knapsackMaximumValue(knapsackWeight - amount, newValues, newWeights);
	}
}
