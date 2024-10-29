package greatestCommonDivisorWeek2Q3;

public class App {
	
	public static void main(String[] args) 
	{
		System.out.println(greatestCommonDivisor(239,1000));
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
