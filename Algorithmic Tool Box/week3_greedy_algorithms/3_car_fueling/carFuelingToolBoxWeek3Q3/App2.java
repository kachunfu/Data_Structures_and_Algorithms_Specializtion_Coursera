package carFuelingToolBoxWeek3Q3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App2 {
	
public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		int distance , milesFullTank, numberOfStops= 0;
		System.out.println("Enter the distance to the city: ");
		distance = keyboard.nextInt();
		System.out.println("Enter the distance your car can go with a full tank: ");
		milesFullTank = keyboard.nextInt();
		System.out.println("Enter the number of stops: ");
		numberOfStops = keyboard.nextInt();
		
		int[] distanceOfStops = new int[numberOfStops];
		for(int i =0 ; i < numberOfStops; i++)
		{
			System.out.println("Enter the distance of stop "+ (i+1) + " :");
			distanceOfStops[i] = keyboard.nextInt();
		}
		System.out.println(minimumNumberStop(distance, milesFullTank, distanceOfStops));
	}
	
	public static int minimumNumberStop(int distance, int milesFullTank, int[] distanceOfStops)
	{
		if(milesFullTank > 400 || milesFullTank < 1 || distanceOfStops.length > 300 || distanceOfStops.length <1)
		{
			throw new CarFuelingException("invalid input"); 
		}
		
		//Check if the distance from start to first stops and distance from last stop to destination are achievable 
		if(distanceOfStops[0] > milesFullTank || distance- distanceOfStops[distanceOfStops.length -1] > milesFullTank)
		{
			return -1;
		}
		
		int stopCount = 0;
		int distanceCanReach = milesFullTank;
		List<Integer> stopList = new ArrayList<>();
		stopList.add(0);
		for(int i = 0; i < distanceOfStops.length; i++)
		{
			stopList.add(distanceOfStops[i]);
		}
		stopList.add(distance);
	
		//Check if the stops distance are achievable 
		for(int i = 1; i< distanceOfStops.length; i++)
		{
			if(distanceOfStops[i] < distanceOfStops[i-1])
			{
				throw new CarFuelingException("invalid input");
			}
			if(distanceOfStops[i] - distanceOfStops[i-1] > milesFullTank)
			{
				return -1;
			}
		}
		
		for(int i =1; i< stopList.size()-1; i++)
		{
			if(stopList.get(i+1) > distanceCanReach)
			{
				stopCount++;
				distanceCanReach = stopList.get(i) + milesFullTank;
			}
		}
		return stopCount;
	}
}
