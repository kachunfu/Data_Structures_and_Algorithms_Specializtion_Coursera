package maximumPairwiseProductWeek1;

import java.util.Arrays;
import java.util.Random;

public class MaxPairProduct {
	
	public static void main(String[] args) {
		
		stressTest(1000,200000);
	}
	
	public static void stressTest(int n, int m)
	{
		while(true)
		{
			long result1, result2;
			Random random = new Random();
			int randomNumberSize = random.nextInt(2,n);
			;
			int[] testArray = new int[randomNumberSize];
			
			for(int i=0; i< testArray.length; i++) 
			{
				int randomNumber = random.nextInt(0,m);
				testArray[i] = randomNumber;
			}
			System.out.println("Test Array: " + Arrays.toString(testArray));
			
			result1 = maxPairwiseProductNaive(testArray);
			result2 = maxPairwiseProductFast(testArray);
			if (result1 == result2) 
			{
				System.out.println("OK");
			}
			else
			{
				System.out.println("Wrong answer: " + result1 + ", " + result2);
				return;
			}
			
		}
	}
	public static long maxPairwiseProductNaive(int[] sequence)
	{
		//Beginner
		long maxProduct = 0L;
		
		//i = 1, 2, ...n
		for(int i =0 ; i < sequence.length; ++i)
		{
			for(int j = i+1 ; j < sequence.length; ++j)
			{
			maxProduct = (long)Math.max(maxProduct, (long)sequence[i] * (long)sequence[j]);
			}
		}
		
		return maxProduct;
	}
	
	public static long maxPairwiseProductFast(int[] sequence)
	{
		//Advanced
		int index = 0;
		int temp = -99;
				
		for(int i = 1; i<sequence.length; i++)
		{
			if(sequence[i] > sequence[index])
			{
				index = i;
			}
		}
		temp = sequence[sequence.length-1];
		sequence[sequence.length-1] = sequence[index];
		sequence[index] = temp;
		
		temp = -99;
		index = 0;
		for(int j = 1; j< sequence.length -1 ; j++)
		{
			if(sequence[j] > sequence[index])
			{
				index = j;
			}
		}
		temp = sequence[sequence.length-2];
		sequence[sequence.length-2] = sequence[index];
		sequence[index] = temp;
		
		return (long)sequence[sequence.length-2] * (long)sequence[sequence.length-1];
		
	}

}
