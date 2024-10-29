package fibonacciNumberAgainWeek2Q5;

public class App {
	

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		System.out.println(fibonacci(239, 1000));
		long endTime = System.currentTimeMillis();
		long runtime = endTime - startTime;
		System.out.println("Runtime : " + runtime + " ms");
		
		System.out.println(pisano(12));
		
	}

	public static int fibonacci(long n, int mod)
	{
		if(n == 1 || n == 2)
		{
			return (int) (n%mod);
		}
		int [] numbersMod = new int[mod*mod];
		numbersMod[0] = 0;
		numbersMod[1] = 1;
		int length = 0;
		for(int i = 2; i < mod*mod ; i++)
		{
			numbersMod[i]= (numbersMod[i-2]+ numbersMod[i-1])%mod;
			if(numbersMod[i] % mod == 1 && numbersMod[i-1] % mod ==0)
			{
				length = i-1;
			}
		}
		System.out.println(length);
		int [] numbers = new int[(int) (n%length+1)];
		
		numbers[0] = 0;
		numbers[1] = 1;
		
		for(int i = 2; i < numbers.length; i++)
		{
			numbers[i]= (numbers[i-1]+numbers[i-2])%mod;
		}
		return numbers[numbers.length-1];
		
	}

	public static long pisano(long mod)
	{
		long prev = 0;
		long curr = 1;
		long result = 0;
		
		for(int i = 0; i < mod*mod; i ++)
		{
			long temp = 0;
			temp = curr;                      //1
			curr = (prev + curr)%mod;         //(0+1)%mod
			prev = temp;
			
			if(prev == 0 && curr ==1)
				result = i+1;
		}
		return result;
	}
	

}
