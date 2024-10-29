package collectingSignaturesToolBoxWeek3Q5;

import java.util.Arrays;
import java.util.Scanner;

public class App {

	public static void main(String[] args) 
	{
		
		Scanner keyboard = new Scanner(System.in);
		int number = 0;
		System.out.println("Enter the number of segments");
		number = keyboard.nextInt();
		
		TimeSlot[] arrays = new TimeSlot[number];
		
		for(int i =0; i < number; i++)
		{
			int a,b = 0;
			System.out.println("Enter the number of first number in array " + (i+1));
			a = keyboard.nextInt();
			System.out.println("Enter the number of second number in array " + (i+1));
			b = keyboard.nextInt();
			arrays[i] = new TimeSlot(a,b);
		}
		
		keyboard.close();
		
		collectingSignatures(number, arrays);
	}
	
	public static void collectingSignatures(int numberOfTimeSlot, TimeSlot[] timeSlots)
	{
		int index , optimumIndex;
		index = 0;
		optimumIndex = 0;
		int[] optimumTimeSlot = new int[numberOfTimeSlot];
		
		Arrays.sort(timeSlots);
		
		while(index < numberOfTimeSlot)
		{
			optimumTimeSlot[optimumIndex] = timeSlots[index].getB();
			while(index < numberOfTimeSlot && optimumTimeSlot[optimumIndex] >= timeSlots[index].getA() 
					&& optimumTimeSlot[optimumIndex] <= timeSlots[index].getB())
			{
				index++;
			}
			optimumIndex++;
		}
		
		System.out.println(optimumIndex);
		for(int i = 0; i <optimumIndex; i++ )
		{
			System.out.print(optimumTimeSlot[i] + " ");
		}
		
	}
}
