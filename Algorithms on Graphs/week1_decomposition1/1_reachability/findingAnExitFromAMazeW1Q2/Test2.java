package findingAnExitFromAMazeW1Q2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.Stack;

public class Test2 {

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
		
		graph.DFS();
		System.out.println("Enter two vertices to check their connectivity: ");
		String vv = es.nextLine();
		String[] vvA = vv.split(" ");
		
		int v1 = Integer.parseInt(vvA[0]);
		int v2 = Integer.parseInt(vvA[1]);
		
		System.out.println(graph.checkConnectiveity(v1, v2));
	}
	
	public static class Graph
	{
		Map<Integer, Vertex> vertexMap;
		Map<Vertex, List<Vertex>> adjVertices;
		
		public Graph() {
			this.vertexMap = new HashMap<>();
			this.adjVertices = new HashMap<>();
		}

		public Map<Vertex, List<Vertex>> getAdjVertices() {
			return adjVertices;
		}

		public void setAdjVertices(Map<Vertex, List<Vertex>> adjVertices) {
			this.adjVertices = adjVertices;
		}

		void addVertex(int label)
		{
			Vertex vertex = new Vertex(label);
			vertexMap.put(label, vertex);
			adjVertices.putIfAbsent(vertex, new ArrayList<>());
		}
		
		Vertex getVertex(int label)
		{
			return adjVertices.keySet().stream().filter(key -> key.getLabel()==label)
					.findFirst().orElseThrow(() -> new IllegalArgumentException("Vertex not found"));
		}
		
		void removeVertex(int label)
		{
			Vertex v = new Vertex(label);
			adjVertices.values().stream().forEach(e -> e.remove(v));
			adjVertices.remove(new Vertex(label));
		}
		
		void addEdge(int label1, int label2)
		{
			Vertex v1 = vertexMap.get(label1);
			Vertex v2 = vertexMap.get(label2);
				
			adjVertices.get(v1).add(v2);
			adjVertices.get(v2).add(v1);
		}
		
		String checkConnectiveity(int label1, int label2)
		{
			Optional<Vertex> v1 = adjVertices.keySet().stream()
								.filter(key -> key.getLabel()==label1).findFirst();
			
			Optional<Vertex> v2 = adjVertices.keySet().stream()
								.filter(key -> key.getLabel()==label2).findFirst();
			
			if(!v1.isPresent() || !v2.isPresent())
				return "0";
			
			if(v1.get().CCnum == v2.get().CCnum)
				return "1";
			else
			{
				System.out.println(v1.get().CCnum  + " " + v2.get().CCnum );
				return "0";
			}
				
			
		}
		void explore(Vertex v, int CCnum)
		{
			Stack<Vertex> stack = new Stack<>();
			stack.push(v);
			
			while(!stack.isEmpty())
			{
				Vertex current = stack.pop();
				
				if(!current.isVisited)
				{
					current.isVisited = true;
					current.CCnum = CCnum;
					System.out.println("cuurent label : "+ current.label + " ; current CCnum: "+ current.CCnum + " ; current isVisited : " + current.isVisited);
					for(Vertex w: adjVertices.get(current))
					{
						if(!w.isVisited)
						{
							stack.push(w);
						}
					}
				}
			}
		}
		
		void DFS()
		{
			for(Vertex v: adjVertices.keySet())
			{
				v.isVisited=false;
				v.CCnum = 0;
			}
			int cc = 1;
			for(Vertex v: adjVertices.keySet())
			{
				if(!v.isVisited)
				{
					explore(v, cc);
					cc++;
				}
			}
		}
	}
	
	public static class Vertex
	{
		int label;
		boolean isVisited;
		int CCnum;

		public Vertex(int label) 
		{
			this.label = label;
			this.CCnum = 0;
			this.isVisited = false;
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

		public int getLabel() {
			return label;
		}

		public int getCCnum() {
			return CCnum;
		}
	}
	
	public static class EasyScanner
	{
		int nextInt()
		{
			Scanner s = new Scanner(System.in);
			int i = s.nextInt();
			return i;
		}
		
		String nextLine()
		{
			Scanner s = new Scanner(System.in);
			String str = s.nextLine();
			return str;
		}
	}
}
