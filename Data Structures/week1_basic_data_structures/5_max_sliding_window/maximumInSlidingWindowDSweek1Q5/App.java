package maximumInSlidingWindowDSweek1Q5;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class App {

	public static void main(String[] args) 
	{
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the number of elements : ");
		int number = keyboard.nextInt();
		int[] numbers = new int[number];
		for(int i =0; i< number; i++)
		{
			System.out.println("Enter element " + (i+1) + " : ");
			numbers[i] = keyboard.nextInt();
		}
		System.out.println("Enter stack size : ");
		int sSize = keyboard.nextInt();
		
		maxSlidingWindow(numbers, sSize);
	}
	
	public static void maxSlidingWindow(int[] numbers, int sSize)
	{
		Deque<Integer> queue = new LinkedList<>();
		int size = numbers.length;
		
		
		int i;
		for(i = 0; i< sSize; i++)
		{
			int value = numbers[i];
			while(!queue.isEmpty() && value >= numbers[queue.peekLast()])
				queue.removeLast();
			
			queue.addLast(i);

		}
		for(;i< size; i++)
		{
			int value = numbers[i];
			System.out.println(numbers[queue.peek()] + " ");
			while((!queue.isEmpty()) && queue.peek() <= i - sSize)
				queue.removeFirst();
			while((!queue.isEmpty()) && value >= numbers[queue.peekLast()])
				queue.removeLast();
			
			queue.addLast(i);
		}
		System.out.print(numbers[queue.peek()]);
	}
	
}
