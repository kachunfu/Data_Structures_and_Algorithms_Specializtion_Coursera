package phoneBookDirectAddressDSweek3Q1;

import java.util.Scanner;

public class App {
	
	static String[] names = new String[(int) Math.pow(10, 7)];

	public static void main(String[] args) {
		
		EasyScanner es = new EasyScanner();
		System.out.println("Enter number of queries: ");
		int q = es.nextInt();
		
		for(int i = 0; i< q; i++)
		{
			System.out.println("Enter query seperated entities by space :");
			String s = es.nextLine();
			phoneBookManager(s);
		}
		
	}
	
	public static void phoneBookManager(String query)
	{
		String[] queryA = query.split(" ");
		String operation = queryA[0];
		char c = operation.charAt(0);
		int number = Integer.parseInt(queryA[1]);
		
		switch(c)
		{
			case 'a':
			{
				String name = queryA[2];
				add(number, name);
				break;
			}
			
			case 'd': del(number);break;
			
			case 'f':
			{
				System.out.println(find(number));
				break;
			}
			
		}
	}
	public static void add(int index, String name)
	{
		names[index] = name;
	}
	
	public static void del(int index)
	{
		names[index] = null;
	}
	
	public static String find(int index)
	{
		if(names[index] != null)
			return names[index];
		else
			return "not found";
			
	}

	public static class EasyScanner{
		
		public static int nextInt()
		{
			Scanner es = new Scanner(System.in);
			int i = es.nextInt();
			return i;
		}
		
		public static String nextLine()
		{
			Scanner es = new Scanner(System.in);
			String s = es.nextLine();
			return s;
		}
	}
}
