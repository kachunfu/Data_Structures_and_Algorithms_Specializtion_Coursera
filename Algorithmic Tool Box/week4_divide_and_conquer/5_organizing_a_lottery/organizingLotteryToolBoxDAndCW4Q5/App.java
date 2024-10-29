package organizingLotteryToolBoxDAndCW4Q5;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class App {
	
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("How many segments? ");
		int numberOfSegments = keyboard.nextInt();
		System.out.println("How many points? ");
		int numberOfPoints = keyboard.nextInt();
		
		Element[][] segments = new Element[numberOfSegments][2];
		Element[] points = new Element[numberOfPoints];
		
		for(int i = 0; i < numberOfSegments ; i++)
		{
			System.out.println("First value of segment " + (i+1) + " : ");
			int value = keyboard.nextInt();
			segments[i][0] = new Element(value, -1);
			System.out.println("Second value of segment " + (i+1) + " : ");
			value = keyboard.nextInt();
			segments[i][1] = new Element(value, 1);
		}
		
		for(int i = 0; i < numberOfPoints; i++)
		{
			System.out.println("Value of point " + (i+1) + " : ");
			int value = keyboard.nextInt();
			points[i] = new Element(value, 0, i);
		}
		
		int[] answer = lotteryProcessor(segments,points);
		
		System.out.println(Arrays.toString(answer));
	}
	
	public static int[] lotteryProcessor(Element[][] elements, Element[] points)
	{
		int[] answer = new int[points.length];
		Element[] sortedElements = new Element[elements.length*2 + points.length];
		int index = 0;
		
		for(int i = 0; i < elements.length; i++)
		{
			sortedElements[index++] = elements[i][0];
			sortedElements[index++] = elements[i][1];
		}
		
		for(int i =0; i < points.length; i++)
		{
			sortedElements[index++] = points[i];
		}
		
		quickSort(sortedElements);
		int left = 0; int right = 0; int j = 0;
		int pointPosition = 0;
		
		while(j < index)
		{
			if(sortedElements[j].getPosition()== -1)
				left++;
			if(sortedElements[j].getPosition()==0)
			{
				pointPosition = sortedElements[j].getPointPostion();
				sortedElements[j].setPointPostion(left - right);
				answer[pointPosition] = sortedElements[j].getPointPostion();
			}
			if(sortedElements[j].getPosition()==1)
				right++;
			j++;
		}
		
		return answer;
	}
	
	private static void quickSort(Element[] elements)
	{
		quickSort(elements, 0, elements.length-1);
	}
	
	private static void quickSort(Element[] elements, int left, int right)
	{
		if(left >= right)
			return;
		
		int pivotIndex = choosePivot( left, right);
		int pivot = elements[pivotIndex].getValue();
		swap(elements, pivotIndex, right);
		int leftPointer = partition(elements, left, right, pivot);
		
		quickSort(elements, left, leftPointer-1);
		quickSort(elements, leftPointer + 1, right);
		
	}
	
	private static int partition(Element[] elements, int left, int right, int pivot)
	{
		int leftIndex = left;
		int rightIndex = right;
		
		while(leftIndex < rightIndex)
		{
			while(elements[leftIndex].getValue() <= pivot && leftIndex < rightIndex)
				leftIndex++;
			
			while(elements[rightIndex].getValue() >= pivot && leftIndex < rightIndex)
				rightIndex--;
			
			swap(elements, leftIndex, rightIndex);
		}
		swap(elements, leftIndex, right);
		
		return leftIndex;
	}
	
	private static int choosePivot( int left, int right)
	{
		return new Random().nextInt(right - left) + left;
	}
	
	private static void swap(Element[] elements, int index1, int index2)
	{
		Element temp = elements[index1];
		elements[index1] = elements[index2];
		elements[index2] = temp;
	}

}
