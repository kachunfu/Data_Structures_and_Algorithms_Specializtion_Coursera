package clusteringKruskalDisjointSetW5Q3;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		
		EasyScanner es = new EasyScanner();
		System.out.println("Enter the number the vertices : ");
		int vertices = es.nextInt();
		Graph graph = new Graph(vertices);
		
		for(int i =0; i< vertices; i++)
		{
			System.out.println("Enter the x and y value (,separated by comma) of vertex " + (i+1) + " : ");
			String xy = es.nextLine();
			String[] xyA = xy.split(" ");
			int x = Integer.parseInt(xyA[0]);
			int y = Integer.parseInt(xyA[1]);
			graph.addVertex(i, x, y, i);
		}
		
		System.out.println("Enter the number of clustered sets : ");
		int numS = es.nextInt();
		
		System.out.println(graph.clusteringKruskalMinDistBetweenKSets(numS));
	}
	
	public static class Vertex
	{
		int x,y,parent,rank;

		public Vertex(int x, int y, int parent) {
			this.x = x;
			this.y = y;
			this.parent = parent;
			this.rank = 0;
		}
	}
	
	public static class Edge
	{
		int uId,vId;
		double weight;
		
		public Edge(int uId, int vId, double weight) {
			this.uId = uId;
			this.vId = vId;
			this.weight = weight;
		}
	}
	
	public static class Graph
	{
		int vSize;
		Vertex[] vertices;
		
		public Graph(int vSize) {
			this.vSize = vSize;
			this.vertices = new Vertex[vSize];
		}
		
		double clusteringKruskalMinDistBetweenKSets(int k)
		{
			int numEAdded = 0;
			List<Edge> edges = new LinkedList<>();
			
			for(int i =0; i<vertices.length; i++)
			{
				for(int j = i+1; j < vertices.length; j++)
					edges.add(new Edge(i,j, euclideanDistance(vertices[i], vertices[j])));
			}
			
			quickSort(edges);
			
			for(int i =0; i<edges.size(); i++)
			{
				if(find(edges.get(i).uId, vertices) != find(edges.get(i).vId, vertices))
				{
					numEAdded ++;
					union(edges.get(i).uId, edges.get(i).vId, vertices);
				}
				if(vSize - numEAdded < k)
					return edges.get(i).weight;
			}
			return -1;
			
		}
		
		void addVertex(int i, int x, int y, int p)
		{
			vertices[i] = new Vertex(x, y, p);
		}
		
		int find(int i, Vertex[] setVertices)
		{
			while(i != setVertices[i].parent)
				i = setVertices[i].parent;
			return i;
		}
		
		void union(int i, int j, Vertex[] setVertices)
		{
			int iId = find(i, setVertices);
			int jId = find(j, setVertices);
			
			if(iId == jId)
				return;
			
			if(setVertices[iId].rank > setVertices[jId].rank)
				setVertices[jId].parent =iId;
			else
			{
				setVertices[iId].parent = jId;
				if(setVertices[iId].rank == setVertices[jId].rank)
					setVertices[jId].rank ++;
			}
		}
	}
		
	static double euclideanDistance(Vertex u, Vertex v)
	{
		return Math.sqrt(Math.pow(u.x - v.x, 2)+ Math.pow(u.y - v.y, 2));
	}
		
	static void quickSort(List<Edge> input)
	{
		quickSort(input, 0, input.size()-1);
	}
		
	static void quickSort(List<Edge> input, int left, int right)
	{
		if(left >= right || left < 0)
			return;
			
		int pivotI = choosePivotIndex(left, right);
		double pivot = input.get(pivotI).weight;
		int[] equalIPair = partition(input, left, right, pivot);
			
		quickSort(input, left, equalIPair[0]-1);
		quickSort(input, equalIPair[1]+1, right);
	}
		
	static int[] partition(List<Edge> input, int left, int right, double pivot)
	{
		int leftI = left;
		int rightI = right;
		int equalI = left;
			
		int[] equalIPair = new int[2];
			
		do
		{
			if(input.get(equalI).weight < pivot)
			{
				swap(input, equalI, leftI);
				leftI++;
				equalI++;
			}
			else if(input.get(equalI).weight > pivot)
			{
				swap(input, equalI, rightI);
				rightI--;
			}
			else
			{
				if(input.get(equalI).weight == pivot);
					equalI++;
			}
		}
		while(equalI <= rightI);
			
		equalIPair[0] = leftI;
		equalIPair[1] = rightI;
			
		return equalIPair;
	}
		
	static int choosePivotIndex(int left, int right)
	{
		int pivotI = new Random().nextInt(right - left) + left;
		return pivotI;
	}
		
	static void swap(List<Edge> input, int i1, int i2)
	{
		Edge temp = input.get(i1);
		input.set(i1,input.get(i2));
		input.set(i2, temp);
	}
		

	
	public static class EasyScanner
	{
		String nextLine()
		{
			Scanner s = new Scanner(System.in);
			String str = s.nextLine();
			return str;
		}
		
		int nextInt()
		{
			Scanner s = new Scanner(System.in);
			int i = s.nextInt();
			return i;
		}
	} 
}
