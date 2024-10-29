package moneyChangeAlgorithmToolboxWeek3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class App {
	
	public static void main(String[] args) {
		
//		System.out.println(26/10);
		
		long startTime = System.currentTimeMillis();
		System.out.println(moneyChange2(7029));
		long endTime = System.currentTimeMillis();
		long runtime = endTime - startTime;
		
		System.out.println("Runtime : " + runtime + " ms");
	}

	public static int moneyChange(int amount)
	{
		int maxCoin = 0;
		int[] coins = {1,5,10};
		int numberOfCoins = 0;
		List<Integer> coinList = new ArrayList<>();
		coinList =  Arrays.stream(coins).boxed().sorted((a,b) -> b.compareTo(a))
								.collect(Collectors.toList());
		
		int index = 0;
		while(amount > 0)
		{
			if(amount>= coinList.get(index))
			{
				maxCoin = coinList.get(index);
				amount -= maxCoin;
				numberOfCoins++;
			}else 
			{
				index++;
				maxCoin = coinList.get(index);
				amount -= maxCoin;
				numberOfCoins++;
			}
		}
		return numberOfCoins;
	}
	
	public static int moneyChange2(int amount)
	{
		int numberOfCoins = 0;
		
		while(amount > 0)
		{
			if(amount >= 10)
			{
				amount -=10;
				numberOfCoins++;
			}
			else if(amount >= 5)
			{
				amount -= 5;
				numberOfCoins++;
			}
			else
			{
				amount -= 1;
				numberOfCoins++;
			}
		}
		return numberOfCoins;
	}
	
	public static int moneyChange3(int amount)
	{
		return (amount/10) + (amount%10)/5 + amount%5;
	}
}
