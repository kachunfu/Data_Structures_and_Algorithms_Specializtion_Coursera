package parallelPHeapSortDSPQAndDSweek2Q2;

import java.util.Scanner;

public class App {
	
	public static Thread[] threads;
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter number of thread : ");
		
		int number = keyboard.nextInt();
		threads = new Thread[number];
		
		for(int i = 0; i < number; i++)
			threads[i] = new Thread(i);
		
		System.out.println("Enter number of jobs : ");
		int numberOfJobs = keyboard.nextInt();
		long[] processingTimes = new long[numberOfJobs];
		
		for(int i =0; i< numberOfJobs; i++)
		{
			System.out.println("Enter the processing time for job "+ (i+1) + " : ");
			processingTimes[i] = keyboard.nextLong();
		}
		keyboard.close();
		buildHeap(threads);
		parallelProcessing(processingTimes);
	}
	
	public static void parallelProcessing(long[] processingTimes)
	{
		for(int i = 0; i< processingTimes.length; i++)
		{
			System.out.println(threads[0].getId() + " " + threads[0].getStartTime());
			threads[0].addProcessingTime(processingTimes[i]);
			shiftDown(0);
		}
	}
	public static void buildHeap(Thread[] threads)
	{
		for(int i = threads.length/2 ; i >= 0; i--)
		{
			shiftDown(i);
		}
	}
	
	public static void swap(int i, int j)
	{
		Thread temp = threads[i];
		threads[i] = threads[j];
		threads[j] = temp;
	}
	
	public static int leftChild(int i)
	{
		return i * 2 + 1;
	}
	
	public static int rightChild(int i)
	{
		return i * 2 + 2;
	}

	
	public static void shiftDown(int i)
	{
		int minIndex = i;
		int l = leftChild(i);
		int r = rightChild(i);
		
		if(l <= threads.length-1 && threads[l].getStartTime() == threads[i].getStartTime() && threads[l].getId() < threads[i].getId())
			swap(i, l);
		if(r <= threads.length-1 && threads[r].getStartTime() == threads[i].getStartTime() && threads[r].getId() < threads[i].getId())
			swap(i, r);
		
		if(l <= threads.length-1 && r<= threads.length-1 && threads[l].getStartTime() == threads[r].getStartTime()
				&& threads[r].getId() < threads[l].getId())
		{
			swap(l, r);
			shiftDown(l);
			shiftDown(r);
		}
		
		if(l <= threads.length-1 && threads[l].getStartTime()< threads[minIndex].getStartTime())
			minIndex = l;
		if(r <= threads.length-1 && threads[r].getStartTime() < threads[minIndex].getStartTime())
			minIndex = r;
			
		if(minIndex != i)
		{
			swap(i, minIndex);
			shiftDown(minIndex);
		}
	}
}
