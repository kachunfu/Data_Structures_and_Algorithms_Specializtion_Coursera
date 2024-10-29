package parallelPHeapSortDSPQAndDSweek2Q2;

public class Thread 
{

	int id;
	int startTime;
	
	public Thread(int id) {
		this.id = id;
		this.startTime = 0;
	}

	public int getId() {
		return id;
	}

	public int getStartTime() {
		return startTime;
	}
	
	public void addProcessingTime(long time)
	{
		this.startTime += time;
	}
	
}
