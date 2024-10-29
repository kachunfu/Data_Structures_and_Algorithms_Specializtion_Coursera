package maximumAdvertisementRevenueW3Q3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int num = 0;
		
		System.out.println("Please enter the number of the sequnce");
		num = keyboard.nextInt();
		Integer[] profitPerClick = new Integer[num];
		Integer[] averageNumberOfClicksPerDayOfSlot = new Integer[num];
		
		for(int i =0; i< profitPerClick.length; i++)
		{
			System.out.println("Please enter the " + (i+1) + "th profit per click ");
			profitPerClick[i] = keyboard.nextInt();
			
			System.out.println("Please enter the " + (i+1) + "th average number of clicks per day of slot ");
			averageNumberOfClicksPerDayOfSlot[i] = keyboard.nextInt();
		}
		keyboard.close();
		
		System.out.println(maximumAdvertisementRevenue(profitPerClick, averageNumberOfClicksPerDayOfSlot));
	}
	
	public static int maximumAdvertisementRevenue(Integer[] profitPerClick, Integer[] averageNumberOfClicksPerDayOfSlot)
	{
		Comparator<Integer> descendingComparator = new Comparator<>()
				{
					@Override
					public int compare(Integer o1, Integer o2) 
					{
						
						return o2.compareTo(o1);
					}
				};
		Arrays.sort(profitPerClick, descendingComparator);	
		Arrays.sort(averageNumberOfClicksPerDayOfSlot, descendingComparator );
		
		int maxProfit = 0;
		for(int i =0; i< profitPerClick.length; i++)
		{
			maxProfit += averageNumberOfClicksPerDayOfSlot[i] * profitPerClick[i];
		}
		return maxProfit;
	}
}
