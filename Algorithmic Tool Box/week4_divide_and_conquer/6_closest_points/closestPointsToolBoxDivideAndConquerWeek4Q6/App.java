package closestPointsToolBoxDivideAndConquerWeek4Q6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import testingProgram.ClosestPoints4.Point;

public class App {
	
	public static void main(String[] args) {
		
//		Point[] points = {new Point(4,4), new Point(-2, -2), new Point(-3,-4), new Point(-1,3), new Point(2,3), new Point(-4, 0),
//				new Point(1,1), new Point(-1,-1), new Point(3,-1), new Point(-4,2), new Point(-2,4)
//		};
		
//		Point[] points = {new Point(7,7), new Point(1, 100), new Point(4, 8), new Point(7, 7)
//		};
		
		Point[] points = {new Point(0,0), new Point(3, 4)
		};
		
//		Random rand = new Random();
//		int size = rand.nextInt(2,100000);
//		Point[] points = new Point[size];
//		
//		for(int i =0; i < size; i++)
//		{
//			points[i] = new Point(rand.nextInt(-1000000000, 1000000000), rand.nextInt(-1000000000, 1000000000));
//		}
		
//		Scanner keyboard = new Scanner(System.in);
//		System.out.println("How many points? ");
//		int amount = keyboard.nextInt();
//		Point[] points = new Point[amount];
//		for(int i =0; i< amount; i++)
//		{
//			int x; int y;
//			System.out.println("Enter x value of point " + (i+1) + " : ");
//			x = keyboard.nextInt();
//			System.out.println("Enter y value of point " + (i+1) + " : ");
//			y = keyboard.nextInt();
//			points[i] = new Point(x,y);
//		}
		
		quickSort(points);
		List<Point> pointsList = new ArrayList<>();
		for(int i = 0; i < points.length; i++)
		{
			pointsList.add(points[i]);
		}
		
		int midIndex = points.length/2;
		int medianX = points[midIndex].getX();
		
		double firstHalfMin = segmentMinDistance(pointsList, 0, midIndex-1);
		double secondHalfMin = segmentMinDistance(pointsList, midIndex,  points.length-1);
		double fSMin = Math.min(firstHalfMin, secondHalfMin);

		List<Point> segment = new ArrayList<>();
		for(int i =0; i< points.length; i++)
		{
			if(points[i].getX() >= (medianX-fSMin) && points[i].getX()<= (medianX+fSMin))
				segment.add(points[i]);
		}
		
		List<Point> sortYList = mergeSort(segment);
		
		int i=0; int j=0;
		double segmentMinD = Double.MAX_VALUE;
		
		while(i<sortYList.size())
		{
			int jIndexBound = i+7;
			if(i > sortYList.size()-7)
				jIndexBound = sortYList.size();
			j = i+1;
			while(j < jIndexBound)
			{
				double distance = distance(sortYList.get(i), sortYList.get(j));
				if(distance <= segmentMinD)
					segmentMinD = distance;
				j++;
			}
		    i++;
		}
		
		double resultMin = Math.min(fSMin, segmentMinD);
		
		System.out.println(resultMin);
	}
	
	private static List<Point> mergeSort(List<Point> points)
	{
		if(points.size()==1)
			return points;
		
		List<Point> first = new ArrayList<>();
		List<Point> second = new ArrayList<>();
		
		int mid = points.size()/2;
		
		for(int i =0; i < mid; i++)
		{
			first.add(points.get(i));
		}
		
		for(int i = 0; i< (points.size()-mid) ; i++)
		{
			second.add(points.get(i + mid));
		}
		
		first = mergeSort(first);
		second = mergeSort(second);
		
		return merge(first,second);
	}
	
	private static List<Point> merge(List<Point> first, List<Point> second)
	{
		int i = 0; int j =0;
		int size1 = first.size();
		int size2 = second.size();
		List<Point> result = new ArrayList<>();
		while(i < size1 && j < size2)
		{
			if(first.get(i).getY() <= second.get(j).getY())
				result.add(first.get(i++));
			else
				result.add(second.get(j++));
		}
		while(i< size1)
			result.add(first.get(i++));
		while(j< size2)
			result.add(second.get(j++));
			
		return result;
	}
	
	private static double segmentMinDistance(List<Point> segment, int index1, int index2)
	{
		double segmentMin = Double.MAX_VALUE;
		for(int i =index1; i < index2 + 1; i++)
		{
			for(int j = i+1; j < index2 + 1; j++)
			{
				double distance = distance(segment.get(i), segment.get(j));
				if(distance <= segmentMin)
					segmentMin = distance;
			}
		}
		return segmentMin;
	}
	
	private static double distance(Point point1, Point point2)
	{
		double distance = Math.sqrt(Math.pow(point1.getX()-point2.getX(), 2) + Math.pow(point1.getY()-point2.getY(), 2));
		return distance;
	}
	
	private static void swap(Point[] points, int index1, int index2)
	{
		Point temp = points[index1];
		points[index1]= points[index2];
		points[index2] = temp;
	}
	
	private static int choosePivot(int left, int right)
	{
		return new Random().nextInt(right - left) + left;
	}
	
	private static void quickSort(Point[] points)
	{
		quickSort(points, 0, points.length-1);
	}
	private static void quickSort(Point[] points, int left, int right)
	{
		if(left>=right)
			return;
		
		int pivotIndex = choosePivot(left, right);
		int pivot = points[pivotIndex].getX();
		swap(points, pivotIndex, right);
		int leftPointer = partition(points, left, right, pivot);
		quickSort(points, left, leftPointer-1);
		quickSort(points, leftPointer+1, right);
	}
	
	private static int partition(Point[] points, int left, int right, int pivot)
	{
		int leftIndex = left;
		int rightIndex = right;
		while(leftIndex < rightIndex)
		{
			while(points[leftIndex].getX() <= pivot && leftIndex < rightIndex)
				leftIndex++;
			while(points[rightIndex].getX() >= pivot && leftIndex < rightIndex)
				rightIndex--;
			swap(points, leftIndex, rightIndex);
		}
		swap(points, leftIndex, right);
		return leftIndex;
	}

}
