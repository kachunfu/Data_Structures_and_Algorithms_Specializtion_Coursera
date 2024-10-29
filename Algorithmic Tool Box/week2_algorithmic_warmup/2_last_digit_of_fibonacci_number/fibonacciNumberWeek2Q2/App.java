package fibonacciNumberWeek2Q2;

public class App {
	
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		System.out.println(fibonacci(239));
		long endTime = System.currentTimeMillis();
		long runtime = endTime - startTime;
		System.out.println("Runtime : " + runtime + " ms");
	}

	public static long fibonacci(int n)
	{
		if(n== 0)
		{
			return 0;
		}
		if(n<=2)
		{
			return 1;
		}
		else 
		{
			int [] numbers = new int[n+1];
			numbers[0] = 0;
			numbers[1] = 1;
			
			for(int i = 2; i < numbers.length; i++)
			{
				numbers[i]= (numbers[i-1]+numbers[i-2])%10;
			}
			return numbers[numbers.length-1];
		}
	}
}
