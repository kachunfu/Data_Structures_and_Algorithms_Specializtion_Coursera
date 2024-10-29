package binarySearchToolBoxDivideAndConquerWeek4Q1;

import java.util.Arrays;
import java.util.Scanner;

public class App {
	
	public static void main(String[] args) 
	{
		int size, size2;
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the number of elements of a sorted array: ");
		size = keyboard.nextInt();
		
		int[] sortedArray = new int[size];
		for(int i=0; i < size; i++)
		{
			System.out.println("Enter elements " + (i+1) + " of a sorted array: ");
			sortedArray[i] = keyboard.nextInt();
		}
		
		System.out.println("Enter the number of elements of the array to search: ");
		size2 = keyboard.nextInt();
		int[] arrayToSearch = new int[size2];
		for(int i = 0 ; i< size2; i++)
		{
			System.out.println("Enter elements " + (i+1) + " of a the array to search : ");
			arrayToSearch[i] = keyboard.nextInt();
		}
		
		System.out.println(Arrays.toString(searchIndexArray(sortedArray, arrayToSearch)));
		
	}

	public static int binarySearch(int[] sortedArray, int lowIndex, int highIndex, int key)
	{
		int midIndex = 0;
		if(highIndex< lowIndex)
			return -1;
		
		midIndex = (lowIndex + (highIndex - lowIndex)/2);
		if(key == sortedArray[midIndex])
			return midIndex;
		else if(key <  sortedArray[midIndex])
			return binarySearch(sortedArray, lowIndex, midIndex-1, key);
		else 
			return binarySearch(sortedArray, midIndex+1, highIndex, key);
	}
	
	public static int binarySearchIt(int[] sortedArray, int lowIndex, int highIndex, int key)
	{
		int midIndex = Integer.MIN_VALUE;
		while(lowIndex <= highIndex)
		{
			midIndex = lowIndex + (highIndex- lowIndex)/2;
			if(key == sortedArray[midIndex])
				return midIndex;
			else if(key < sortedArray[midIndex])
				highIndex = midIndex-1;
			else lowIndex = midIndex+1;
		}
		return -1;
	}
	
	public static int[] searchIndexArray(int[] sortedArray, int[] searchingElements)
	{
		int[] answer = new int[searchingElements.length];
		
		for(int i = 0; i<searchingElements.length ; i++)
		{
			answer[i] = binarySearchIt(sortedArray, 0, sortedArray.length-1, searchingElements[i]);
		}
		return answer;
	}
	
}
