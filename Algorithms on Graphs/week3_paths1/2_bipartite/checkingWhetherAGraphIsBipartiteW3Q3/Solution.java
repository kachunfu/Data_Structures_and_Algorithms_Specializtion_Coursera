package checkingWhetherAGraphIsBipartiteW3Q3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


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
		
		int a = graph.getRandomKey();
		Vertex randomVertex =graph.getVertex(a);
		System.out.println(graph.BFSIfBipartite(randomVertex));
		
		
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
		
		int getRandomKey()
		{
			Set<Integer> keySet = vertexMap.keySet();
			List<Integer> list = new ArrayList<>(keySet);
			
			Random random = new Random();
			
			int randomIndex = random.nextInt(list.size());
			
			return list.get(randomIndex);
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
		
		String BFSIfBipartite(Vertex a)
		{
			Map<Vertex, Boolean> TFMap = new HashMap<>();
			
			for(Vertex u: adjVertices.keySet())
			{
				TFMap.put(u, null);
			}
			TFMap.put(a, true);
			
			Deque<Vertex> deque = new ArrayDeque<>();
			deque.add(a);
			
			while(!deque.isEmpty())
			{
				Vertex current = deque.remove();
				
				for(Vertex v: adjVertices.get(current))
				{
					if(TFMap.get(v)==null && TFMap.get(current)==true)
					{
						TFMap.put(v, false);
						deque.add(v);
					}
					else if(TFMap.get(v)==null && TFMap.get(current)== false)
					{
						TFMap.put(current, true);
						deque.add(v);
					}
					else if(TFMap.get(v)!= null && TFMap.get(current)!= null && TFMap.get(v) == TFMap.get(current))
						return "1";
					}
				}
			return "0";
		}
	}
	
	public static class Vertex
	{
		int label;

		public Vertex(int label) 
		{
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
