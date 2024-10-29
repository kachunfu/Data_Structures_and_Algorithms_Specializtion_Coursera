package maximumSalaryToolBoxWeek3Q7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {
	
	public static void main(String[] args) {
		
		int length = 0;
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter length of the array");
		length = keyboard.nextInt();
		Integer[] numbersArray = new Integer[length];
		
		for(int i = 0 ; i <length ; i++)
		{
			System.out.println("Enter element " + (i+1) + " of the array: ");
 			numbersArray[i] = keyboard.nextInt();
		}
		
		System.out.println(largestNumber(numbersArray));
	}

	public static String largestNumber(Integer[] numbersArray)
	{
		List<Integer> numbersList = new ArrayList<>();
		Collections.addAll(numbersList, numbersArray);
		String answer = "";
		Integer maxDigit;
		
		while(!numbersList.isEmpty())
		{
			maxDigit = numbersList.get(0);
			
			for(int i =0; i< numbersList.size(); i++)
			{
				if(numbersList.get(i)>= maxDigit)
				{
					maxDigit = numbersList.get(i);
					
					if(Math.log(numbersList.get(i))+ 1 == 1 && Math.log(maxDigit) + 1 ==1)
					{
						maxDigit = numbersList.get(i);
					}
		
					else if(Math.log(numbersList.get(i))+ 1 > Math.log(maxDigit) + 1 && numbersList.get(i) % (Math.log(maxDigit) + 1) >= maxDigit)
					{
						maxDigit = numbersList.get(i);
					}
						
				}
			}
			answer += maxDigit;
			numbersList.remove(maxDigit);
		}
		
		
		
		return answer;
		
	}
}
