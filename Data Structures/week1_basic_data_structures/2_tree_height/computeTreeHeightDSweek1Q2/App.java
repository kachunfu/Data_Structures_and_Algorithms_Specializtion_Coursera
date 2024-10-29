package computeTreeHeightDSweek1Q2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class App {

	static ArrayList<ArrayList<Integer>> trees = new ArrayList<>();
	
	static int MAX =  100000;

	
	public static void main(String[] args) 
	{
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Enter the number of nodes : ");
		
		int number = keyboard.nextInt();
		
		int[] parentNodes = new int[number];
		
		for(int i = 0; i< number; i++)
		{
			System.out.println("Enter index " + (i+1) + " : ");
			parentNodes[i] = keyboard.nextInt();
		}

		for(int i = 0; i < MAX ; i ++)
		{
			trees.add(new ArrayList<Integer>());
		}
		
		int rootIndex = buildTrees(parentNodes);
		System.out.println(breathFirst(rootIndex, trees));
	}
	
	
	public static int buildTrees( int[] parentNodes)
	{
		int rootIndex = Integer.MIN_VALUE;
		for(int i = 0; i< parentNodes.length; i++)
		{
			if(parentNodes[i] == -1)
				rootIndex = i;
			else
			{
				/* Add child node to parent node */
				trees.get(parentNodes[i]).add(i);
				/* Add parent node to parent node */
				trees.get(i).add(parentNodes[i]);
			}
		}
		return rootIndex;
	}
	
	public static int breathFirst(int rootIndex, ArrayList<ArrayList<Integer>> trees)
	{
		Queue<ArrayList<Integer>> queue = new LinkedList<>();
		Map<Integer, Integer> isVisited = new HashMap<>();
		
		int max_level_reached = 0;
		
		// height of root node is zero
		queue.add(new ArrayList<Integer>(Arrays.asList(rootIndex, 1)));
		
		ArrayList<Integer> queueList = new ArrayList<>();
		
		while(!queue.isEmpty())
		{
			queueList = queue.remove();
			
			isVisited.put(queueList.get(0), 1);
			
			System.out.println("Parent node is : " + queueList.get(0));
			
			max_level_reached = Math.max(max_level_reached, queueList.get(1));
			
			for(int i = 0; i< trees.get(queueList.get(0)).size(); i++)
			{
				if(!isVisited.containsKey(trees.get(queueList.get(0)).get(i)))
					queue.add(new ArrayList<Integer>(Arrays.asList(trees.get(queueList.get(0)).get(i), 
							queueList.get(1)+1 )));
			}
		}
		return max_level_reached;
	}
	
	
}
