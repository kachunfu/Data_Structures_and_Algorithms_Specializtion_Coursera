package fibonacciNumberAgainWeek2Q5;

import java.util.ArrayList;
import java.util.List;

public class BetterSolution {
	
	
	public static void main(String[] args) {
		
//		System.out.println(fibonacciWithpisanoPeriod(2015,3));
		
		long startTime = System.currentTimeMillis();
		System.out.println(fibonacciWithpisanoPeriod(239,1000));
		long endTime = System.currentTimeMillis();
		long runtime = endTime - startTime;
		System.out.println("Runtime : " + runtime + " ms");
		
//		System.out.println(pisanoPeriod(1000));
	}
	
	
	public static long fibonacciWithpisanoPeriod(long number, int mod)
	{
		List<Long> numbers = new ArrayList<>();
		long first, second, total, index, periodIndex;
		first = index = periodIndex = 0;
		second = total = 1;
		numbers.add(first);
		numbers.add(second);

		for(index = 2;;index++)
		{
			total = (first + second)%mod;
			numbers.add(total);
			
			first = second;
			second = total;
	
			if(numbers.get((int) index) == 1 && numbers.get((int) index-1) ==0) 
			{
				periodIndex = index -1;
				break;
			}
		}
			
		number %= periodIndex;
		return numbers.get((int)number);
		
	}
	
	//!!!!!
//	public static long pisanoPeriod( int mod)
//	{
//		List<Long> numbers = new ArrayList<>();
//		long first, second, total, index, periodIndex;
//		first = index = periodIndex = 0;
//		second = total = 1;
//		numbers.add(first);
//		numbers.add(second);
//
//		for(index = 2;;index++)
//		{
//			total = (first + second)%mod;
//			numbers.add(total%mod);
//			
//			first = second;
//			second = total;
//	
//			if(numbers.get((int) index) == 1 && numbers.get((int) index-1) ==0) 
//			{
//				periodIndex = index -1;
//					break;
//			}
//			System.out.println("index: " + index + "; periodIndex: " + periodIndex + " ; element: " + numbers.get((int) index));
//		}
//
//		return periodIndex;
//	}
}
