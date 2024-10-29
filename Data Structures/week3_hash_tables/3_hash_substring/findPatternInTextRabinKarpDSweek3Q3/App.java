package findPatternInTextRabinKarpDSweek3Q3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
	
	public static int p = 1000000007;
	public static int x;
	
	public static void main(String[] args) {
		
		EasyScanner es = new EasyScanner();
		System.out.println("Enter pattern : ");
		String p = es.nextLine();
		System.out.println("Enter text :");
		String t = es.nextLine();
		
		List<Integer> list = rabinKarp(t,p);
		list.forEach(e-> System.out.print(e + " "));
		
	}
	
	public static int[] precomputeHashes(String text, int patternSize)
	{
		int[] hashes = new int[text.length()-patternSize + 1];
		String str = text.substring(text.length()-patternSize, text.length());
		
		hashes[text.length()-patternSize] = polyHash(str);
		long y = 1;
		
		for(int i = 1; i<= patternSize; i++)
			y = (int)((y*x)%p+p)%p;
		
		for(int i = text.length()-patternSize -1; i>=0; i--)
		{
			hashes[i] = (int)(((x*(long)hashes[i+1] + (long)text.charAt(i) - y* (long)text.charAt(i+patternSize))%p + p)%p);
		}
		return hashes;
	}
	
	public static List<Integer> rabinKarp(String text, String pattern)
	{
		x = new Random().nextInt(1, p-1);
		List<Integer> result = new ArrayList<>();
		int pHash = polyHash(pattern);
		int[] hashes = precomputeHashes(text, pattern.length());
		
		for(int i = 0; i <= text.length()-pattern.length(); i++)
		{
			if(pHash!= hashes[i])
				continue;
			if(areEqual(text.substring(i, i+pattern.length()), pattern))
				result.add(i);
		}
		return result;
	}
	
	public static boolean areEqual(String s1, String s2)
	{
		if(s1.length()!=s2.length())
			return false;
		
		for(int i =0; i< s1.length(); i++)
		{
			if(s1.charAt(i)!= s2.charAt(i))
				return false;
		}
		return true;
	}
	
	public static int polyHash(String s)
	{
		long hash = 0;
		
		for(int i = s.length()-1; i>= 0; i--)
		{
			hash = (int)((hash * x + (long) s.charAt(i))%p + p)%p;
		}
		return (int)hash;
	}

	public static class EasyScanner
	{
		public static String nextLine()
		{
			Scanner s = new Scanner(System.in);
			String str = s.nextLine();
			return str;
		}
	}
}
