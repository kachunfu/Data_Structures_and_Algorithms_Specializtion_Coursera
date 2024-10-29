package maximumNumberOfPrizesToolBoxWeek3Q6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App4 {
	
	public static void main(String[] args) 
	{
		Scanner keyboard = new Scanner(System.in);
		long input = 0;
		System.out.println("enter a number : ");
		input = keyboard.nextLong();
		
		NumberOfPrice ans = totalNumberOfsumSequence(input);
		System.out.println(ans.getNumberOfPrice());
		System.out.println(ans.getPriceList());
		keyboard.close();
	}
	
	public static NumberOfPrice totalNumberOfsumSequence(long input)
	{
		List<Long> sequenceList = new ArrayList<>();
		long element = 1;
		long sum = 0;
		
		while(sum < input)
		{
			if(sum + 2*element + 1 > input )
			{
				while(element <= input - (element-1) && sum + element < input)
				{
					element++;
				}
				sum+= element;
				sequenceList.add(element);
				if(sum==input)
					return new NumberOfPrice(sequenceList.size(), sequenceList);
			}
				sum+= element;
				sequenceList.add(element);
				element++;
		}
		return new NumberOfPrice(sequenceList.size(), sequenceList);
	}
}

