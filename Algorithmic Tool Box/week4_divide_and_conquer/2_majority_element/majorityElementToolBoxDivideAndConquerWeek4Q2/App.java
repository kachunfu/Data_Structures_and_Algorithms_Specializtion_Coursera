package majorityElementToolBoxDivideAndConquerWeek4Q2;

import java.util.Arrays;
import java.util.Scanner;

public class App {
	
	public static void main(String[] args) 
	{
		int size;
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the size of the array: ");
		size = keyboard.nextInt();
		int[] numbers = new int[size];
		for(int i =0; i < size; i++)
		{
			System.out.println("Enter element " + (i+1) + " : ");
			numbers[i] = keyboard.nextInt();
		}
		System.out.println();
		System.out.println(hasMajority(getMajority(numbers, 0, numbers.length-1)));
		
		keyboard.close();
	}

	public static int getMajority(int[] input, int left, int right)
	{
		if(left == right)
			return input[left];
		
		int mid = left + (right-left)/2;
		int leftMajority = getMajority(input, left, mid);
		int rightMajority = getMajority(input, mid+1, right);
		
		if(leftMajority == rightMajority)
			return leftMajority;
		
		int leftCount = checkFrequency(input, left, right, leftMajority);
		int rightCount = checkFrequency(input, left, right, rightMajority);
		
		if(leftCount > (right-left+1)/2)
			return leftMajority;
		else if(rightCount > (right-left+1)/2)
			return rightMajority;
		else
		    return 0;
	}
	
	public static int checkFrequency(int[] input, int left, int right, int majority)
	{
		int count = 0;
		
		for(int i = left; i <= right; i++)
		{
			if(input[i] == majority)
			{
				count ++;
			}
		}
		return count;
	}
	
	public static int hasMajority(int getMajority)
	{
		if(getMajority!=0)
			return 1;
		else
			return 0;
	}
}
