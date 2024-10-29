package maximumSalaryToolBoxWeek3Q7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class App9 {
	
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
			maxDigit = -99999;
			for(int i =0; i< numbersList.size(); i++)
			{
				if(isGreaterOrEqual(numbersList.get(i), maxDigit))
				{
					maxDigit = numbersList.get(i);
				}

				System.out.println("Max : " + maxDigit);
			}
			answer += maxDigit;
			numbersList.remove(maxDigit);
		}
		return answer;
	}
	
	public static boolean isGreaterOrEqual(Integer digit, Integer maxDigit)
	{
		String digitStr = digit.toString();
		String maxDigitStr = maxDigit.toString();
		String combinedNumber = digitStr + maxDigitStr;
		String combinedNumber2 = maxDigitStr + digitStr;

		return combinedNumber.compareTo(combinedNumber2)>=0;
	}
}
