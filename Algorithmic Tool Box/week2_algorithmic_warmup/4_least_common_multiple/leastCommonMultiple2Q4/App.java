package leastCommonMultiple2Q4;

public class App {
	
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		System.out.println(leastCommonMultiple(21,8));
		long endTime = System.currentTimeMillis();
		long runtime = endTime -  startTime;
		System.out.println("Runtime: " + runtime + " ms");
		
	}

	public static int leastCommonMultiple(int first, int second)
	{
		int gcd = greatestCommonDivisor(first,second);
		return second/ gcd * first;
	}
	
	public static int greatestCommonDivisor(int first, int second) 
	{
		int[] gcdPair = new int[2];
		if(first>=second)
		{
			gcdPair[0] = first;
			gcdPair[1] = second;
		}
		else
		{
			gcdPair[0] = second;
			gcdPair[1] = first;
		}
		if(first ==0)
		{
			return second;
		}
		if(second ==0)
		{
			return first;
		}
		return greatestCommonDivisor(second, first%second);
	}
}
