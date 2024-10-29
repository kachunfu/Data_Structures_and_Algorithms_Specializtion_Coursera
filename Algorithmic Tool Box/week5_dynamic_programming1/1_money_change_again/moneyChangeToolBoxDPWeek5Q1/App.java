package moneyChangeToolBoxDPWeek5Q1;

import java.util.Scanner;

public class App {
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the amount of money");
		int money = keyboard.nextInt();
		int [] coins = {1,3,4};
		System.out.println(moneyChangeDP(money, coins));
		
		keyboard.close();
		
	}

	public static int moneyChangeDP(int money, int[] coins)
	{
		int[] minNumCoins = new int [money+1];
		minNumCoins[0] = 0;
		
		for(int m = 1 ; m <= money ; m++)
		{
			minNumCoins[m] = Integer.MAX_VALUE;
			
			for(int i = 0; i < coins.length; i++)
			{
				if(m >= coins[i])
				{
					int numCoins = minNumCoins[m - coins[i]]+1;
					if(numCoins < minNumCoins[m])
						minNumCoins[m] = numCoins;
				}
			}
		}
		return minNumCoins[money];
		
	}
}
