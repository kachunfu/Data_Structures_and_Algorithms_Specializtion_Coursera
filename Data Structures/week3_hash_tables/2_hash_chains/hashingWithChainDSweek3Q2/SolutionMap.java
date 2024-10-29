package hashingWithChainDSweek3Q2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SolutionMap {
	
	public static long x = 263;
	public static long p = 1000000007;
	public static Map<Integer, List<String>> hashTable = new HashMap<>();
	public static int size;
	
	public static void main(String[] args) 
	{
		EasyScanner es = new EasyScanner();
		System.out.println("Enter number of bucket  : ");
		size = es.nextInt();
		
		for(int i =0; i < size; i++)
		{
			hashTable.put(i,new LinkedList<>());
		}
		System.out.println("Enter number of queries : ");
		int queries = es.nextInt();
		
		for(int i = 0; i < queries ; i++)
		{
			System.out.println("Enter query by separating entity by space : ");
			String query = es.nextLine();
			hashingWithChain(query);
		}
		
	}
	
	public static void hashingWithChain(String s)
	{
		String[] sA = s.split(" ");
		char option = sA[0].charAt(0);
		switch(option)
		{
			case 'a': 
			{
				String str = sA[1];
				add(str);
				break;
			}
			
			case 'c':
			{
				int i = Integer.parseInt(sA[1]);
				check(i);
				break;
			}
			
			case 'f':
			{
				String str = sA[1];
				System.out.println(find(str));;
				break;
			}
			
			case 'd':
			{
				String str = sA[1];
				del(str);
				break;
			}
		}
		
	}
	
	public static void add(String s)
	{
		int index = polyHash(s,size);
		List<String> list = hashTable.get(index);
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).equals(s))
				return;
		}
		list.add(0, s);
	}
	
	public static String find(String s)
	{
		int index = polyHash(s,size);
		List<String> list = hashTable.get(index);
		for(int i = 0; i< list.size(); i++)
		{
			if(list.get(i).equals(s))
				return "yes";
		}
		return "no";
	}
	
	public static void del(String s)
	{
		if(find(s).equals("no"))
			return;
		else
		{
			int index = polyHash(s,size);
			List<String> list = hashTable.get(index);
			list.remove(s);
		}
	}
	
	public static void check(int i)
	{
		List<String> list = hashTable.get(i);
		if(list.isEmpty())
			System.out.println();
		else
		{
			for(String s : list)
			{
				System.out.print(s + " ");
			}
			System.out.println();
		}
	}
	
	public static int polyHash(String s, int m)
	{
		long hash = 0;
		for(int i = s.length()-1; i >= 0; i--)
		{
			//!!!!!!!important polyHash
			hash = ((hash * x + (long)s.charAt(i) )%p + p)%p;
			
		}
		return (int) hash%m;
	}

	public static class EasyScanner
	{
		public int nextInt()
		{
			Scanner s = new Scanner(System.in);
			int i = s.nextInt();
			return i;
		}
		
		public String nextLine()
		{
			Scanner s = new Scanner(System.in);
			String str = s.nextLine();
			return str;
		}
	}

}
