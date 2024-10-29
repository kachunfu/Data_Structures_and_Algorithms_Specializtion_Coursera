package partitioningSouvenirsToolBoxDPWeek6Q2;

import java.util.Scanner;

public class App {
	
	public static void main(String[] args) 
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the size of array : ");
		int size = keyboard.nextInt();
		int[] souvenirs = new int[size];
		
		for(int i =0; i< size; i ++)
		{
			System.out.println("Enter value of element " + (i+1) + " : ");
			souvenirs[i] = keyboard.nextInt();
		}
		System.out.println(partitioningSouvenirs(souvenirs));
		keyboard.close();
	}
	
	public static boolean partitioningSouvenirs(int[] souvenirs)
	{
		int sum =0;
		for(int i =0; i< souvenirs.length;i++)
			sum+= souvenirs[i];
		
		if(sum%3 != 0)
			return false;
		
		boolean[][] partitionMatrix = new boolean[souvenirs.length+1][sum/3+1];
		for(int i = 0; i<= souvenirs.length; i++)
			partitionMatrix[i][0] = true;
		for(int j =0; j <= sum/3; j++)
			partitionMatrix[0][j] = false;
		
		for(int i = 1; i<= souvenirs.length; i++)
		{
			for(int j = 1; j<= sum/3; j++)
			{
				partitionMatrix[i][j] = partitionMatrix[i-1][j];
				
				if(j >= souvenirs[i-1])
				{
					if(partitionMatrix[i-1][j-souvenirs[i-1]]==true)
						partitionMatrix[i][j] = true;
				}
			}
		}
		return partitionMatrix[souvenirs.length][sum/3];
	}

}
