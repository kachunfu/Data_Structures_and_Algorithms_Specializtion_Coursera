package maximumSalaryToolBoxWeek3Q7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class FinalSolution {
	
	public static void main(String[] args) {
		
		int length = 0;
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter length of the array");
		length = keyboard.nextInt();
		int[] numbersArray = new int[length];
	
		for(int i = 0 ; i <length ; i++)
		{
			System.out.println("Enter element " + (i+1) + " of the array: ");
 			numbersArray[i] = keyboard.nextInt();
		}
		keyboard.close();
		System.out.println(largestNumber(numbersArray));
	}

	public static String largestNumber(int[] numbersArray)
	{
		List<Integer> numbersList = new ArrayList<>();
		for(int i = 0; i < numbersArray.length; i++)
		{
			numbersList.add(numbersArray[i]);
		}
		String answer = "";
		int maxDigit;
		while(!numbersList.isEmpty())
		{
			maxDigit = Integer.MIN_VALUE;
			for(int i =0; i< numbersList.size(); i++)
			{
				if(isGreaterOrEqual(numbersList.get(i), maxDigit))
				{
					maxDigit = numbersList.get(i);
				}

			}
			answer += maxDigit;
			numbersList.remove((Integer)maxDigit);
		}
		return answer;
	}
	
	public static boolean isGreaterOrEqual(int digit, int maxDigit)
	{
		String combinedNumber ="";
		String combinedNumber2 ="";
		combinedNumber += digit;
		combinedNumber += maxDigit;
		combinedNumber2 +=  maxDigit;
		combinedNumber2 += digit;
		int int1, int2;
		
		int1 = Integer.parseInt(combinedNumber.replaceAll("-2147483648", ""));
		int2 = Integer.parseInt(combinedNumber2.replaceAll("-2147483648", ""));
		
		if(int1 >= int2)
		{
			return true;
		}else
			return false;
	}
}
