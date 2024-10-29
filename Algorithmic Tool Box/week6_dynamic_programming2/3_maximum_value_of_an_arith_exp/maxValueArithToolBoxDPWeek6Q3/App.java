package maxValueArithToolBoxDPWeek6Q3;

import java.util.Scanner;

public class App {
	
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter an arithmetic expression : ");
		String arith = keyboard.nextLine();
		int[] digits;
		char[] ops;
		if(arith.charAt(0) == '+' || arith.charAt(0) == '-' || arith.charAt(0) == '*')
		{
			arith = " " + arith;
			digits = new int[arith.length()/2];
			ops = new char[arith.length()/2];
			ops[0] = arith.charAt(1);
			
			for(int i = 2; i< arith.length();i++)
			{
				if(i%2==0)
					digits[i/2-1] = Character.getNumericValue(arith.charAt(i));
				else
					ops[i/2] = arith.charAt(i);
			}
		}
		else
		{
			arith = "  " + arith;
			digits = new int[(arith.length()-1)/2];
			ops = new char[(arith.length()-1)/2 -1];
			
			for(int i = 2; i< arith.length();i++)
			{
				if(i%2==0)
					digits[i/2-1] = Character.getNumericValue(arith.charAt(i));
				else
					ops[i/2-1] = arith.charAt(i);
			}
		}
		
		keyboard.close();
		System.out.println(parentheses(digits, ops));
		
	}
	
	public static MinMax minAndMax(MinMax minMax, int i, int j, char[] ops)
	{
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int a = 0; int b =0; int c = 0; int d = 0;
		
		for(int k = i ; k <= j-1; k++)
		{
			if(ops[k-1] == '+')
			{
				a = minMax.getElementMax(i, k) + minMax.getElementMax(k+1, j);
				b = minMax.getElementMax(i, k) + minMax.getElementMin(k+1, j);
				c = minMax.getElementMin(i, k) + minMax.getElementMax(k+1, j);
				d = minMax.getElementMin(i, k) + minMax.getElementMin(k+1, j);
			}
			else if(ops[k-1] == '-')
			{
				a = minMax.getElementMax(i, k) - minMax.getElementMax(k+1, j);
				b = minMax.getElementMax(i, k) - minMax.getElementMin(k+1, j);
				c = minMax.getElementMin(i, k) - minMax.getElementMax(k+1, j);
				d = minMax.getElementMin(i, k) - minMax.getElementMin(k+1, j);
			}
			else
			{
				a = minMax.getElementMax(i, k) * minMax.getElementMax(k+1, j);
				b = minMax.getElementMax(i, k) * minMax.getElementMin(k+1, j);
				c = minMax.getElementMin(i, k) * minMax.getElementMax(k+1, j);
				d = minMax.getElementMin(i, k) * minMax.getElementMin(k+1, j);
			}
			min = min(min,a,b,c,d);
			max = max(max,a,b,c,d);
			minMax.setElementMax(i, j, max);
			minMax.setElementMin(i, j, min);
		}
		return minMax;
	}
	
	public static int parentheses(int[] digits, char[] ops)
	{
		int[][] min = new int[digits.length+1][digits.length+1];
		int[][] max = new int[digits.length+1][digits.length+1];
		for(int i = 1; i<= digits.length; i++)
		{
			min[i][i] = digits[i-1];
			max[i][i] = digits[i-1];
		}
		
		MinMax minMax = new MinMax(min, max);
		for(int s = 1; s <= digits.length-1; s++)
		{
			for(int i = 1; i <= digits.length-s; i++)
			{
				int j = i+s;
				minMax = minAndMax(minMax, i, j, ops);
			}
		}
		return minMax.getElementMax(1, digits.length);
	}
	
	public static int min(int min, int a, int b, int c, int d)
	{
		if(min <= a && min <= b && min <= c && min<= d)
			return min;
		else if(a <= min && a<=b && a<=c && a<=d)
			return a;
		else if(b <= min && b<=a && b<=c && b<=d)
			return b;
		else if(c<=min && c<=a && c<=b && c<=d)
			return c;
		else
			return d;
	}
	public static int max(int max, int a, int b, int c, int d)
	{
		if(max >= a && max >= b && max>= c && max>= d)
			return max;
		else if(a >= max && a>=b && a>=c && a>=d)
			return a;
		else if(b >= max && b>=a && b>=c && b>=d)
			return b;
		else if(c>=max && c>=a && c>=b && c>=d)
			return c;
		else
			return d;
	}

}
