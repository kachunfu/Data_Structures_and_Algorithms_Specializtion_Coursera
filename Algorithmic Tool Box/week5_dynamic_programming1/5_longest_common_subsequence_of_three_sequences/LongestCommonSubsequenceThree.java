package testingProgram;

import java.util.Scanner;

public class LongestCommonSubsequenceThree {
	
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter size of Array 1: ");
		int size1 = keyboard.nextInt();
		int[] array1 = new int[size1];
		for(int i =0; i< size1 ; i++)
		{
			System.out.println("Enter element " + (i+1) + " of array 1 : ");
			array1[i] = keyboard.nextInt();
		}
		System.out.println("Enter size of Array 2: ");
		int size2 = keyboard.nextInt();
		int[] array2 = new int[size2];
		for(int i =0; i< size2 ; i++)
		{
			System.out.println("Enter element " + (i+1) + " of array 2 : ");
			array2[i] = keyboard.nextInt();
		}
		System.out.println("Enter size of Array 3: ");
		int size3 = keyboard.nextInt();
		int[] array3 = new int[size3];
		for(int i =0; i< size3 ; i++)
		{
			System.out.println("Enter element " + (i+1) + " of array 3 : ");
			array3[i] = keyboard.nextInt();
		}
		
		System.out.println(longestCommonSubseqThree(array1, array2, array3));
		
		
		
	}
	
	public static int longestCommonSubseqThree(int[] array1, int[] array2, int[] array3)
	{
		int[][][] lCS = new int[array1.length+1][array2.length+1][array3.length+1];
		
		for(int i =0; i < array1.length; i++)
			lCS[i][0][0] = 0;
		for(int j =0; j < array2.length; j++)
			lCS[0][j][0] = 0;
		for(int k =0; k < array3.length; k++)
			lCS[0][0][k] = 0;
		
		for(int k = 1; k <= array3.length; k++)
		{
			for(int j = 1; j<= array2.length; j++)
			{
				for(int i =1; i <= array1.length; i++)
				{
					if(array1[i-1]==array2[j-1] && array1[i-1]==array3[k-1])
						lCS[i][j][k] = lCS[i-1][j-1][k-1]+1;
					else
						lCS[i][j][k] = max(lCS[i-1][j][k], lCS[i][j-1][k], lCS[i][j][k-1]);
				}
			}
		}
		return lCS[array1.length][array2.length][array3.length];
	}
	
	public static int max(int int1 , int int2, int int3)
	{
		if(int1 >= int2 && int1 >= int3)
			return int1;
		else if(int2 >= int1 && int2 >= int3)
			return int2;
		else
			return int3;
	}

}
