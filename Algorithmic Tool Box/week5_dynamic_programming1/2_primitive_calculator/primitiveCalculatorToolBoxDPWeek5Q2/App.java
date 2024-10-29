package primitiveCalculatorToolBoxDPWeek5Q2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the value : ");
		int value = keyboard.nextInt();
		ValueOp valueOp = primitiveCal(value);
		System.out.println("The answer is : " + valueOp.getMinOp());
		valueOp.getValueOp().forEach(e-> System.out.print(e + " ") );
		keyboard.close();
	}
	
	public static ValueOp primitiveCal(int value)
	{
		int[] minOpIndex = new int[value];
		List<List<Integer>> sequenceList = new ArrayList<>();
		List<Integer> sequence = new ArrayList<>();
		sequence.add(1);
		sequenceList.add(sequence);
		minOpIndex[0] = 0;
		
		for(int v = 1; v < value; v++)
		{	
			int minOp = minOpIndex[v-1]+1;
			List<Integer> sequenceTemp = new ArrayList<>(sequenceList.get(v-1));
			sequenceTemp.add(v+1);
			
			if((v+1) % 2 == 0 &&  minOpIndex[(v+1)/2 -1]+1 < minOp)
			{
				sequenceTemp = new ArrayList<>(sequenceList.get((v+1)/2 -1));
				sequenceTemp.add(v+1);
				minOp = minOpIndex[(v+1)/2 -1]+1;
			}
			else if((v+1)%3 ==0 && minOpIndex[(v+1)/3 -1]+1 < minOp)
			{
				sequenceTemp = new ArrayList<>(sequenceList.get((v+1)/3 -1));
				sequenceTemp.add(v+1);
				minOp = minOpIndex[(v+1)/3 -1]+1;
			}
			minOpIndex[v] = minOp;
			sequenceList.add(sequenceTemp);
		}
		return new ValueOp(minOpIndex[value-1], sequenceList.get(value-1));
	}
}
