package sumOfTwoDigitsWeek1;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		int number1, number2;
		String input;
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter two digits separated by space. Each digit has to be between 0 and 9.");
		input = keyboard.nextLine();
		String[] parts = input.split(" ");
		number1 = Integer.parseInt(parts[0]);
		number2 = Integer.parseInt(parts[1]);
		if(number1 < 0 || number1 > 9 || number2 < 0 || number2 > 9)
		{
			throw new SumException("invalid digit value");
		}
		else
		{
			System.out.println(number1 + number2);
		}

	}

}
