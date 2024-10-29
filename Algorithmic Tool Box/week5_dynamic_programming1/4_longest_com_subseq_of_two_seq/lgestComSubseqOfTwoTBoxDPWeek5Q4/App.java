package lgestComSubseqOfTwoTBoxDPWeek5Q4;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter size of Array 1 :");
		int size1 = keyboard.nextInt();
		int[] array1 = new int[size1];
		for(int i = 0; i < size1; i++)
		{
			System.out.println("Enter element " + (i+1) + " in Array 1: ");
			array1[i] = keyboard.nextInt();
		}
		System.out.println("Enter size of Array 2 : ");
		int size2 = keyboard.nextInt();
		int[] array2 = new int[size2];
		for(int i = 0; i< size2; i++)
		{
			System.out.println("Enter element " + (i+1) + " in Array 2: ");
			array2[i] = keyboard.nextInt();
		}
		
		System.out.println(longestCommonSubseqTwo(array1, array2));
		
	}
	
	public static int longestCommonSubseqTwo(int[] array1, int[] array2)
	{
		int[][] lCS = new int[array1.length+1][array2.length+1];
		
		for(int i =0; i< lCS.length; i++)
			lCS[i][0] =0;
		for(int j =0; j < lCS[0].length; j++)
			lCS[0][j] =0;
		
		for(int j =1; j<= array2.length; j++)
		{
			for(int i =1; i<= array1.length; i++)
			{
				if(array1[i-1] == array2[j-1])
					lCS[i][j] = lCS[i-1][j-1] +1;
				else
					lCS[i][j] = max(lCS[i-1][j], lCS[i][j-1]);
			}
		}
		
		return lCS[array1.length][array2.length];
	}
	
	public static int max(int int1, int int2)
	{
		return int1 >= int2 ? int1:int2;
	}
}
