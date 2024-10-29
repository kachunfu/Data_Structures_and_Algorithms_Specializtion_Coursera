package collectingSignaturesToolBoxWeek3Q5;

public class TimeSlot implements Comparable<TimeSlot>{
	
	private int a;
	private int b;
	
	public TimeSlot(int a, int b) {

		this.a = a;
		this.b = b;
	}

	
	public int getA() {
		return a;
	}


	public int getB() {
		return b;
	}


	@Override
	public int compareTo(TimeSlot o) {
		return Integer.compare(this.b, o.b);
	}
}
