package checkBracketsIntheCodeDSweek1Q1;

import java.util.Scanner;
import java.util.Stack;

public class App {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the string : ");
		String s = keyboard.nextLine();
		System.out.println(checkBracket(s));
		
	}
	
	public static String checkBracket(String s)
	{
		Stack<Character> stack = new Stack<>();
		
		for(int i =0; i< s.length(); i++)
		{
			if(s.charAt(i) != '[' && s.charAt(i) != '{' && s.charAt(i) != '(' 
					&& s.charAt(i) != ']' && s.charAt(i) != '}' && s.charAt(i) != ')' )
				continue;
			
			if(s.charAt(i) == '[' || s.charAt(i) == '{' || s.charAt(i) == '(')
			{
				stack.push(s.charAt(i));
			}
			else
			{
				if(stack.isEmpty())
					return String.valueOf(i+1);
				char top = stack.pop();
				if(top == '[' && s.charAt(i) != ']' 
						|| top == '{' && s.charAt(i) != '}' 
						|| top == '(' && s.charAt(i) != ')')
					return String.valueOf(i+1);
			}
		}
		if(stack.isEmpty())
			return "Success";
		else
		{
			String firstIndex = "";
			for(int i =0; i< s.length(); i++)
			{
				if(stack.firstElement() == s.charAt(i))
					firstIndex = String.valueOf(i+1);
			}
			return firstIndex;
		}
	}
}
