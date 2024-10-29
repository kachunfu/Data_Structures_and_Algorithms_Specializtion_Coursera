package improvingQuickSortToolBoxDAndCrWeek4Q3;

import java.util.Arrays;
import java.util.Random;

public class App {
	
	public static void main(String[] args) {
		
//		Scanner keyboard = new Scanner(System.in);
//		System.out.println("Enter the size of array: ");
//		int size;
//		size = keyboard.nextInt();
//		int[] numbers = new int[size];
//		for(int i =0; i < size; i ++)
//		{
//			System.out.println("Enter the element " + (i+1) + " in array: ");
//			numbers[i] = keyboard.nextInt();
//		}
		int[] numbers = new int[100];
		Random rand = new Random();
		for(int i =0 ; i<100; i++)
		{
			numbers[i] = rand.nextInt(100);
		}
		quickSort(numbers);
		System.out.println(Arrays.toString(numbers));
//		keyboard.close();
	}
	
	public static void quickSort(int[] input)
	{
		quickSort(input, 0, input.length-1);
	}
	
	public static void quickSort(int[] input, int left, int right)
	{
		if(left >= right || left < 0)
			return;
		
		int pivotIndex = choosePivotIndex(left, right);
		int pivot = input[pivotIndex];
		int[] equalIndexPair = partition(input, left, right, pivot);
		
		quickSort(input, left, equalIndexPair[0]-1);
		quickSort(input, equalIndexPair[1]+1, right);
	}
	
	private static int[] partition(int[] input, int left, int right, int pivot)
	{
		int leftIndex = left;
		int equalIndex = left;
		int rightIndex = right;
		int[] equalIndexPair = new int[2];
	
		do
		{
			if(input[equalIndex] < pivot)
			{
				swap(input, equalIndex, leftIndex);
				leftIndex++;
				equalIndex++;
			}
			else if(input[equalIndex] > pivot)
			{
				swap(input, equalIndex, rightIndex);
				rightIndex--;
			}
			else
			{
				if(input[equalIndex] == pivot)
				{
					equalIndex++;
				}
			}
		}
		while(equalIndex <= rightIndex);
		
		equalIndexPair[0] = leftIndex;
		equalIndexPair[1] = rightIndex;
		
		return equalIndexPair;
	}
	
	private static int choosePivotIndex(int left, int right)
	{
		int pivotIndex = new Random().nextInt(right - left) + left;
		return pivotIndex;
	}
	
	private static void swap(int[] input, int index1, int index2)
	{
		int temp = input[index1];
		input[index1] = input[index2];
		input[index2] = temp;
	}
}
