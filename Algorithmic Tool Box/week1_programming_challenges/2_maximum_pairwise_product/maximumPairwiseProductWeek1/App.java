package maximumPairwiseProductWeek1;

import java.util.Scanner;

public class App {

	public static void main(String[] args) 
	{
		int size;
		int[] sequence;
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the array size: ");
		
		size = keyboard.nextInt();
		sequence = new int[size];
		
		for(int i =0 ; i < size; i++)
		{
			System.out.println("Enter the value of element " + (i+1) + ": ");
			sequence[i]  = keyboard.nextInt();
		}
		long startTime = System.currentTimeMillis();
		System.out.println("Naive");
		System.out.println(maxPairwiseProductNaive(sequence));
		long endTime = System.currentTimeMillis();
		
		long runtime = endTime - startTime;
		System.out.println("Runtime: " + runtime + " milliseconds");
		
		startTime = System.currentTimeMillis();
		System.out.println("Fast");
		System.out.println(maxPairwiseProductFast(sequence));
		endTime = System.currentTimeMillis();
		runtime = endTime - startTime;
		System.out.println("Runtime: " + runtime + " milliseconds");
		
	}
	
	public static int maxPairwiseProductNaive(int[] sequence)
	{
		//Beginner
		int maxProduct = 0;
		
		//i = 1, 2, ...n
		for(int i =0 ; i < sequence.length; ++i)
		{
			for(int j = i+1 ; j < sequence.length; ++j)
			{
			maxProduct = Math.max(maxProduct, sequence[i] * sequence[j]);
			}
		}
		
		return maxProduct;
	}
	
	public static int maxPairwiseProductFast(int[] sequence)
	{
		//Advanced
		int index = 0;
		int index2 = 0;
				
		for(int i = 1; i<sequence.length; i++)
		{
			if(sequence[i] > sequence[index])
			{
				index = i;
			}
		}
		if(index == 0)
		{
			index2 = 1;
		}
		else
		{
			index2 = 0;
		}
		
		for(int j = 1; j< sequence.length; j++)
		{
			if(sequence[j] > sequence[index2] && j!= index)
			{
				index2 = j;
			}
		}
		
		return sequence[index] * sequence[index2];
		
	}

}
