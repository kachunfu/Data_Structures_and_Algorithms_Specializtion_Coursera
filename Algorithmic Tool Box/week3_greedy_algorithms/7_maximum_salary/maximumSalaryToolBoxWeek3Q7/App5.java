package maximumSalaryToolBoxWeek3Q7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class App5 {
	
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
		Comparator<Integer> decendingDigitComparator = new Comparator<>()
				{
					@Override
					public int compare(Integer a, Integer b)
					{
						a = findingDigit(a);
						b = findingDigit(b);
						return b.compareTo(a);
					}
				};
//		Collections.sort(numbersList, decendingDigitComparator);
		System.out.println(numbersList);
		String answer = "";
		Integer maxDigit;
		while(!numbersList.isEmpty())
		{
//			maxDigit = numbersList.get(numbersList.size()-1);
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
	
	public static int findingDigit(Integer input)
	{
		return (int) (Math.floor(Math.log10(input)))+ 1;
	}
	
	public static boolean isGreaterOrEqual(Integer digit, Integer maxDigit)
	{
		if(findingDigit(digit)>=findingDigit(maxDigit))
		{
			int counter = Math.max(findingDigit(digit), findingDigit(maxDigit));
			while(counter > 0)
			{
				if(digit%(Math.pow(10, counter)) > maxDigit%(Math.pow(10, counter)))
				{
					return true;
				}
				else if(digit%(Math.pow(10, counter)) < maxDigit%(Math.pow(10, counter)))
					return false;
				else
					counter--;
			}
			return false;
		}
		else 
		{
			System.out.println("digit 2 is less digit 21");
			int counter1 = findingDigit(digit);
			int counter2 = findingDigit(maxDigit);
			while(counter1 > 0 || counter2>0 )
			{
				if(digit/(Math.pow(10, counter1-1)) > maxDigit/(Math.pow(10, counter2-1)))
				{
					return true;
				}
				else if(digit/(Math.pow(10, counter1-1)) < maxDigit/(Math.pow(10, counter2-1)))
					return false;
				else
					counter1--;
					counter2--;
			}
			return false;
		}	
	}
}
