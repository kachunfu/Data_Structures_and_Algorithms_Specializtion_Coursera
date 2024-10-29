package networkPacketProcessingSimDSweek1Q3;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) 
	{
		
//		Scanner keyboard = new Scanner(System.in);
//		System.out.println("Enter the size of buffer : ");
//		int size = keyboard.nextInt();
//		System.out.println("Enter the number of packets : ");
//		int number = keyboard.nextInt();
//		int[] arrivalT = new int[number];
//		int[] processT = new int[number];
//		for(int i = 0; i < number ; i++)
//		{
//			System.out.println("Enter the arrival time of packet " + (i+1) + " : " );
//			arrivalT[i] = keyboard.nextInt();
//			System.out.println("Enter the process time of packet " + (i+1) + " : ");
//			processT[i] = keyboard.nextInt();
//		}
		
		int size1 = 1;
		int[] arrivalT1 = {0,2,5,5,6};
		int[] processT1 = {1,3,1,3,2};
		
		List<Integer> answer = processingPacketSim(size1, arrivalT1, processT1);
		answer.forEach(e-> System.out.println(e));
	}
	
	public static List<Integer> processingPacketSim(int size, int[] arrivalT, int[] processingT)
	{
		List<Integer> beginningT = new LinkedList<>();
		List<Integer> finishT = new LinkedList<>();
		
		int aT = 0; int pT = 0; int fT = 0;
		
		for(int i = 0; i < processingT.length; i++)
		{
			aT = arrivalT[i]; pT = processingT[i];
			while(true)
			{
				if(finishT.isEmpty())
					break;
				if(finishT.get(0) > aT)
					break;
				finishT.remove(0);
			}
			if(finishT.isEmpty())
			{
				beginningT.add(aT);
				fT = aT+pT;
				finishT.add(fT);
			}
			else
			{
				if(finishT.size()==size)
				{
					beginningT.add(-1);
				}
				else
				{
					beginningT.add(fT);
					finishT.add(fT = pT + finishT.get(finishT.size()-1));
				}
			}
		}
		return beginningT;
	}
}
