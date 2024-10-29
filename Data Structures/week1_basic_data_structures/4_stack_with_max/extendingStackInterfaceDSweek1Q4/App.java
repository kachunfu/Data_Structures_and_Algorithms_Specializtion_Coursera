package extendingStackInterfaceDSweek1Q4;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class App {
	
	public static void main(String[] args) {
		
		EasyScanner es = new EasyScanner();
		System.out.println("Enter the number of operations : ");
		int number = es.nextInt();
		
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> maxS = new Stack<>();
		List<Integer> maxList = new ArrayList<>();
		
		for(int i = 0 ; i< number ; i++)
		{
			System.out.println("Enter operation " + (i+1) + " : ");
			String op = es.nextString();
			
			if(op.length() > 4 &&op.substring(0,4).equals("push"))
			{
				int value = Integer.parseInt(op.substring(5, op.length()));
				stack.push(value);
				if(maxS.isEmpty())
					maxS.push(value);
				else if(value > maxS.peek())
					maxS.push(value);
				else
					maxS.push(maxS.peek());
			}
			else if(op.equals("max"))
			{
				maxList.add(maxS.peek());
			}
			else
			{
				stack.pop();
				maxS.pop();
			}
				
		}
		maxList.forEach(e-> System.out.println(e));
	}
}
