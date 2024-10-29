package convertArrayIntoHeapDSPQAndDSweek2Q1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

	static int[] numbers;
	static List<String> swaps = new ArrayList<>();
	
	public static void main(String[] args) 
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the size of the array :");
		int size = keyboard.nextInt();
		numbers = new int[size];
		for(int i = 0; i< size; i++)
		{
			System.out.println("Enter element " + (i+1) + " : ");
			numbers[i] = keyboard.nextInt();
		}
	
		buildHeap(numbers);
		System.out.println(swaps.size());
		swaps.forEach(e-> System.out.println(e));
	}
	
	public static void buildHeap(int[] numbers)
	{
		for(int i = numbers.length/2; i>=0; i--)
		{
			shiftDown(i);
		}
	}
	
	public static void swap(int i, int j)
	{
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
	public static int leftChild(int i)
	{
		return i*2 +1;
	}
	public static int rightChild(int i)
	{
		return i*2 +2;
	}
	
	public static void shiftDown(int i)
	{
		int minIndex = i;
		int leftChildIndex = leftChild(i);
		
		if(leftChildIndex <= numbers.length-1 && numbers[leftChildIndex] < numbers[minIndex])
			minIndex = leftChild(i);
		
		int rightChildIndex = rightChild(i);
		
		if(rightChildIndex <= numbers.length-1 && numbers[rightChildIndex] < numbers[minIndex])
			minIndex = rightChild(i);
		
		if(minIndex != i)
		{
			swaps.add(i + " " + minIndex);
			swap(i,minIndex);
			shiftDown(minIndex);
		}
			
	}
}
