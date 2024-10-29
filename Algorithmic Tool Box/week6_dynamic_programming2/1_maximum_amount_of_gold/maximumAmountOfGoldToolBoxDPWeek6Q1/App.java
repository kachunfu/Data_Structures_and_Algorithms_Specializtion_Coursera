package maximumAmountOfGoldToolBoxDPWeek6Q1;

import java.util.Scanner;

public class App {
	
	public static void main(String[] args) {
			
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Enter the capacity in weight: ");
			int capacity = keyboard.nextInt();
			System.out.println("Enter number of bar ");
			int numBar = keyboard.nextInt();
			int[] weights = new int[3];
			for(int i = 0; i < numBar; i++)
			{
				System.out.println("Enter the weight of bar " + (i+1) + " : ");
				weights[i] = keyboard.nextInt();
			}
			
			System.out.println(knapsackDp(capacity, weights));
			
			keyboard.close();
		}
		
		public static int knapsackDp(int capacity, int[] weights)
		{
			int[][] values = new int[capacity+1][weights.length+1];
			for(int i = 0; i < values.length; i++)
				values[i][0] = 0;
			for(int j = 0; j< values[0].length; j++)
				values[0][j] = 0;
			
			for(int j  = 1; j <= weights.length; j++)
			{
				for(int w = 1; w<= capacity; w++)
				{
					values[w][j] = values[w][j-1];
					if(weights[j-1] <= w)
					{
						int value = values[w - weights[j-1]][j-1] + weights[j-1];
						if(values[w][j] < value)
							values[w][j] = value;
					}
				}
			}
			return values[capacity][weights.length];
		}
		
		public static int max(int int1, int int2)
		{
			return int1 >= int2 ? int1 : int2;
		}

}
