package editDistanceToolBoxDPWeek5Q3;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter string 1 : ");
		String st1 = keyboard.nextLine();
		System.out.println("Enter string 2 : ");
		String st2 = keyboard.nextLine();
		
		System.out.println(editDistance(st1,st2));
		keyboard.close();
	
		
	}
	
	public static int editDistance(String st1, String st2)
	{
		int[][] distance = new int[st1.length()+1][st2.length()+1];
		
		for(int i = 0 ; i < distance.length; i++)
			distance[i][0] = i;
		for(int j = 0; j < distance[0].length; j++)
			distance[0][j] = j;
		
		for(int j = 1; j <= st2.length(); j++)
		{
			for(int i = 1; i <= st1.length(); i++)
			{
				if(st1.charAt(i-1) == st2.charAt(j-1))
					distance[i][j] = min(distance[i][j-1] +1 , distance[i-1][j] +1, distance[i-1][j-1]);
				else
					distance[i][j] = min(distance[i][j-1] +1, distance[i-1][j]+1, distance[i-1][j-1] +1);
			}
		}
		return distance[st1.length()][st2.length()];
	}
	
	public static int min(int int1, int int2, int int3)
	{
		if(int1 <= int2 && int1 <= int3)
			return int1;
		else if(int2 <= int1 && int2 <= int3)
			return int2;
		else 
			return int3;
	}
}
