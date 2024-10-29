package fibonacciNumberAgainWeek2Q6;

import java.util.ArrayList;
import java.util.List;

public class BetterSolution {
	
	public static void main(String[] args) {
		
		System.out.println(sumOfFibonacciNumbers(10000055500007L));
		
	}
	
	public static long sumOfFibonacciNumbers(long input)
	{
		if(input<=1) return 1;
		
		long index, periodIndex, total, sum, first, second; 
		first = index = periodIndex = total = 0;
		sum = second = 1;
		List<Long> numbers = new ArrayList<>();
		numbers.add(0L);
		numbers.add(1L);
		
		for(index = 2;;index++)
		{
			total = (first + second)%10;
			sum = (sum + total)%10;
			numbers.add(sum%10);
			
			first = second;
			second = total;
			///VERY IMPORTANT
			if(numbers.get((int)index).equals(numbers.get((int)periodIndex)))
			{
				periodIndex ++;
				if(index == 2*periodIndex - 1 && index%2==1)
				break;
			}
			///VERY IMPORTANT
			else
			{
				periodIndex=0;
				if(numbers.get((int)index).equals(numbers.get((int)periodIndex)))periodIndex++;
			}
//		System.out.println("index: " + index + "; periodIndex: " + periodIndex + " ; element: " + numbers.get((int) index));
		}
//		System.out.println("AFTER FOR LOOP; index: " + index + "; periodIndex: " + periodIndex + " ; element: " + numbers.get((int) index));
		input %=  periodIndex;
		
		return numbers.get((int)input);

		
	}
}
