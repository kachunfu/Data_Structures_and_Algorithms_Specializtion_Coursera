package sumOfTwoDigitsWeek1;

public class SumException extends RuntimeException{

	public SumException() 
	{
		super("error in Sum of two digits application");
	}
	
	public SumException(String message)
	{
		super(message);
	}
}
