package fibonacciNumberAgainWeek2Q5;

public class App2 {
	

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		System.out.println(fibonacci(239, 1000));
		long endTime = System.currentTimeMillis();
		long runtime = endTime - startTime;
		System.out.println("Runtime : " + runtime + " ms");
		
	}

	public static long fibonacci(long n, long mod)
	{
		if(n == 0)
		{
			return 0;
		}
		if(n == 1)
		{
			return 1;
		}
		
		long length =  pisano(mod);
		long numberF = n % length;
		System.out.println("numberF is : " + numberF);
		
		long prev = 0;
		long curr = 1;
		
		//START AT INDEX 1 !!!!!!!!!!!!!!!!!!!!!!  Interval from index to numberF is numberF -1 !!!!
		for(int i = 0; i < numberF -1; i ++)
		{
			long temp = 0;
			temp = curr;                      //1
			curr = (prev + curr) %mod;         //(0+1)%mod
			prev = temp;
		}
	
		return (curr % mod);
		
		
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
		System.out.println("result : " + result);
		return result;
	}
	

}
