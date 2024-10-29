package computingTheMinimumNumberOfFlightSegmentsW3Q2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import checkWhetherAnyIntersectionInACityW2Q4.Solution.EasyScanner;
import checkWhetherAnyIntersectionInACityW2Q4.Solution.Graph;

public class Solution {
	
	public static void main(String[] args) {
		
		EasyScanner es = new EasyScanner();
		System.out.println("Enter number of vertices and number of edges, seperated by space ");
		String ve = es.nextLine();
		String[] veA = ve.split(" ");
		int v = Integer.parseInt(veA[0]);
		int e = Integer.parseInt(veA[1]);
		
		Graph graph = new Graph();
		
		for(int i = 0; i< v; i++)
		{
			graph.addVertex(i+1);
		}
		
		for(int i =0; i<e ; i++)
		{
			System.out.println("Enter two connected vertices, seperated by space: ");
			String vv = es.nextLine();
			String[] vvA = vv.split(" ");
			
			int v1 = Integer.parseInt(vvA[0]);
			int v2 = Integer.parseInt(vvA[1]);
			graph.addEdge(v1, v2);
		}
		
		System.out.println("Enter the starting Vertex and ending Vertex, separated by space: ");
		String vv = es.nextLine();
		String[] vvA = vv.split(" ");
		
		int v1 = Integer.parseInt(vvA[0]);
		int v2 = Integer.parseInt(vvA[1]);
		
		List<Vertex> list = graph.reconstructPath(graph.getVertex(v1), graph.getVertex(v2), graph.BFSprev(graph.getVertex(v1)));
		graph.printDistance(list);
		
	}
	
	public static class Graph
	{
		Map<Integer, Vertex> vertexMap;
		Map<Vertex, List<Vertex>> adjVertices;
		
		public Graph() {
			this.vertexMap = new HashMap<>();
			this.adjVertices = new HashMap<>();
		}
		
		Vertex getVertex(int label)
		{
			return vertexMap.get(label);
		}
		
		void addVertex(int label)
		{
			Vertex v = new Vertex(label);
			vertexMap.put(label, v);
			adjVertices.put(v, new ArrayList<>());
		}
		
		void addEdge(int label1, int label2)
		{
			Vertex v1 = vertexMap.get(label1);
			Vertex v2 = vertexMap.get(label2);
			
			adjVertices.get(v1).add(v2);
			adjVertices.get(v2).add(v1);
		}
		
		Map<Vertex, Vertex> BFSprev(Vertex a)
		{
			Map<Vertex, Integer> dist = new HashMap<>();
			Map<Vertex, Vertex> prev = new HashMap<>();
			
			for(Vertex u: adjVertices.keySet())
			{
				dist.put(u, Integer.MAX_VALUE);
				prev.put(u, null);
			}
			dist.put(a, 0);
			
			Deque<Vertex> deque = new ArrayDeque<>();
			
			deque.add(a);
			while(!deque.isEmpty())
			{
				Vertex current = deque.remove();
				
				for(Vertex v: adjVertices.get(current))
				{
					if(dist.get(v) == Integer.MAX_VALUE)
					{
						deque.add(v);
						dist.put(v, dist.get(current) + 1);
						prev.put(v, current);
					}
				}
			}
			return prev;
		}
		void printPath(List<Vertex> list)
		{
			if(list.size() != 0)
				list.forEach(e->System.out.print(e.label + " "));
			else
				System.out.println(-1);
		}
		
		void printDistance(List<Vertex> list)
		{
			if(list.size() != 0)
				System.out.println(list.size());
			else
				System.out.println(-1);
		}
		List<Vertex> reconstructPath(Vertex a, Vertex u, Map<Vertex, Vertex> prev)
		{
			List<Vertex> result = new ArrayList<>();
			Vertex start = getVertex(a.label);
			Vertex current = getVertex(u.label);
			
			while(current != null && !current.equals(start))
			{
				result.add(current);
				current = prev.get(current);
			}
			if(current == null)
				return new ArrayList<>();
			
			return reverse(result);
		}
	}
	
	public static class Vertex
	{
		int label;

		public Vertex(int label) {
			this.label = label;
		}

		@Override
		public int hashCode() {
			return Objects.hash(label);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Vertex other = (Vertex) obj;
			return label == other.label;
		}
	}

	static List<Vertex> reverse(List<Vertex> list)
	{
		List<Vertex> reversed = new ArrayList<Vertex>();
		for(int i =list.size()-1; i>= 0; i--)
		{
			reversed.add(list.get(i));
		}
		return reversed;
	}
	
	public static class EasyScanner
	{
		String nextLine()
		{
			Scanner s = new Scanner(System.in);
			String str = s.nextLine();
			return str;
		}
	}
}
