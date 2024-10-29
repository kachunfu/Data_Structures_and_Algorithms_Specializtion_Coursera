package maximumSalaryToolBoxWeek3Q7;

public class Test {

	public static void main(String[] args) {
		
		System.out.println(findingDigit(92));
		System.out.println(Math.pow(10, 2));
		String number = "";
		number+= 21;
		number+= 2;
		System.out.println(number);
		
		System.out.println(Integer.parseInt(number));
		

		
	}
	
	public static int findingDigit(Integer input)
	{
		return (int) (Math.floor(Math.log10(input)))+ 1;
	}
}
