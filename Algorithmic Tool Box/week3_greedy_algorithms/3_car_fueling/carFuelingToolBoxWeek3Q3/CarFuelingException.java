package carFuelingToolBoxWeek3Q3;

public class CarFuelingException extends RuntimeException{

	public CarFuelingException() {
		super("error in Car Fueling application");
	}


	public CarFuelingException(String message) {
		super(message);
		
	}



	
}
