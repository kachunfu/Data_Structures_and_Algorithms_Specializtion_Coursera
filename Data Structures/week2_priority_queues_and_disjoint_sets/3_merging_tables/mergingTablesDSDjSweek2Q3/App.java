package mergingTablesDSDjSweek2Q3;

import java.util.Scanner;

public class App 
{
	
	static int sum = 0;
	static int[] rank;
	static Node[] nodes;
	
	public static void main(String[] args) 
	{
		EasyScanner es = new EasyScanner();
		System.out.println("Enter number of tables and number of operations separated by space");
		String tO = es.nextLine();
		String[] tOA = tO.split(" ");
		int numT = Integer.parseInt(tOA[0]);
		int numO = Integer.parseInt(tOA[1]);
		
		rank = new int[numT];
		nodes = new Node[numT];
		System.out.println("Enter number of rows in each table separated by space");
		String rows = es.nextLine();
		String[] rowsA = rows.split(" ");
		
		for(int i = 0; i< numT; i++)
		{
			nodes[i] = new Node(i, Integer.parseInt(rowsA[i]));
			if(Integer.parseInt(rowsA[i])> sum)
				sum = Integer.parseInt(rowsA[i]);
		}
		
		for(int i = 0; i< numO; i++)
		{
			System.out.println("Enter two merging table IDs separated by space ");
			String tables = es.nextLine();
			String[] tablesA = tables.split(" ");
			int tableA = Integer.parseInt(tablesA[0]) -1;
			int tableB = Integer.parseInt(tablesA[1]) -1;
			union(tableA, tableB);
			System.out.println(sum);
		}
		
	}
	
	public static int find(int i)
	{
		if(i != nodes[i].getParentI())
			nodes[i].setParentI(find(nodes[i].getParentI()));
		return nodes[i].getParentI();
	}
	
	public static void union(int i, int j)
	{
		int iId = find(i);
		int jId = find(j);
		
		if(iId == jId)
			return;
		
		if(rank[iId] > rank[jId])
		{
			nodes[jId].setParentI(iId);
			nodes[iId].setNumberT(nodes[jId].getNumberT()+ nodes[iId].getNumberT());
			if(nodes[iId].getNumberT()>sum)
				sum = nodes[iId].getNumberT();
		}
		else
		{
			nodes[iId].setParentI(jId);
			nodes[jId].setNumberT(nodes[iId].getNumberT() + nodes[jId].getNumberT());
			if(nodes[jId].getNumberT()>sum)
				sum=nodes[jId].getNumberT();
			if(rank[iId] == rank[jId])
				rank[jId] += 1;
		}
	}
	
	public static class Node
	{
		int parentI;
		int numberT;
		
		public int getParentI() {
			return parentI;
		}
		public void setParentI(int parentI) {
			this.parentI = parentI;
		}
		public int getNumberT() {
			return numberT;
		}
		public void setNumberT(int numberT) {
			this.numberT = numberT;
		}
		
		public Node(int parentI, int numberT) {
			this.parentI = parentI;
			this.numberT = numberT;
		}
	}
	
	public static class EasyScanner
	{
		public int nextInt()
		{
			Scanner sc = new Scanner(System.in);
			int i = sc.nextInt();
			return i;
		}
		
		public String nextLine()
		{
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			return s;
		}
		
	}

}
