package fibonacciNumberAgainWeek2Q7;

public class App {
	
	public static void main(String[] args) 
	{
		long number1 = 3;
		long number2 = 7;
		long startTime = System.currentTimeMillis();
		System.out.println(sumFibonacciNumbers2(number1, number2));
		long endTime = System.currentTimeMillis();
		long runtime = endTime - startTime;
		System.out.println("Runtime : " + runtime + " ms");

	}

	public static long sumFibonacciNumbers2(long number1 , long number2)
	{
		long inputDigit1 = Long.toString(number1).length();
		long inputDigit2 = Long.toString(number2).length();
		
		if(number1==0)
		{
			return sumFibonacciNumbersFast(number2,inputDigit2);
		}
		System.out.println("sum til number2 : " + sumFibonacciNumbersFast(number2, inputDigit2));
		System.out.println("sum til number1 : " + sumFibonacciNumbersFast(number1-1, inputDigit1));
		long ans = Math.abs(sumFibonacciNumbersFast(number2, inputDigit2)- sumFibonacciNumbersFast(number1-1, inputDigit1));
		return ans%10;
	}
	
	public static long sumFibonacciNumbers(long number)
	{
		if(number ==0)
		{
			return 0;
		}
		
		if(number ==1)
		{
			return 1;
		}
		
		long sum = 1;
		long prev = 0;
		long curr = 1;
		
		for(long l = 0 ; l < number - 1; l++) 
		{
			long temp = 0;
			temp = curr;
			curr = (prev + curr);
			sum+= curr;
			prev = temp;

		}
		
		return sum;
	}
	
	public static long sumFibonacciNumbersFast(long n, long digit)
	{
		//!!Sum of F0+F1 ....+ Fn = Fn+2 - 1;
		n +=2;
		
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
			long [] numbers = new long [(int) (n+1)];
			numbers[0] = 0;
			numbers[1] = 1;
			
			for(int i = 2; i < numbers.length; i++)
			{
				numbers[i]= (long) ((numbers[i-1]+numbers[i-2])% (Math.pow(10, digit)));
			}
			return numbers[numbers.length-1] - 1;
		}
	}
}
