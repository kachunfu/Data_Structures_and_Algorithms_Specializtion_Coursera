package maxValueArithToolBoxDPWeek6Q3;

public class MinMax {
	
	int[][] min;
	int[][] max;
	
	public MinMax(int[][] min, int[][] max) 
	{
		this.min = min;
		this.max = max;
	}

	public int[][] getMin() {
		return min;
	}
	
	public int getElementMin(int i, int j)
	{
		return min[i][j];
	}
	
	public int getElementMax(int i, int j)
	{
		return max[i][j];
	}

	public void setMin(int[][] min) {
		this.min = min;
	}

	public int[][] getMax() {
		return max;
	}

	public void setMax(int[][] max) {
		this.max = max;
	}
	
	public void setElementMin(int i, int j, int newValue)
	{
		this.min[i][j] = newValue;
	}
	
	public void setElementMax(int i, int j, int newValue)
	{
		this.max[i][j] = newValue;
	}

}
