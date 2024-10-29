package organizingLotteryToolBoxDAndCW4Q5;

public class Element {
	
	int value;
	int position;
	int pointPostion;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getPointPostion() {
		return pointPostion;
	}
	public void setPointPostion(int pointPostion) {
		this.pointPostion = pointPostion;
	}
	public Element(int value, int position, int pointPostion) {

		this.value = value;
		this.position = position;
		this.pointPostion = pointPostion;
	}
	
	public Element(int value, int position) {

		this.value = value;
		this.position = position;
	}
	
	

}
