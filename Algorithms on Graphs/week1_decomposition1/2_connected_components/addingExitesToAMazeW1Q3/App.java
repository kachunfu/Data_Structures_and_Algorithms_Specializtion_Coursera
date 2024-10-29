package addingExitesToAMazeW1Q3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class App {
	
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
		System.out.println(graph.DFSCC());
		
	}
	
	public static class Graph
	{
		Map<Integer, Vertex> vertexMap;
		Map<Vertex, List<Vertex>> adjVertices;
		
		public Graph() {
			this.vertexMap = new HashMap<>();
			this.adjVertices = new HashMap<>();
		}

		public Map<Integer, Vertex> getVertexMap() {
			return vertexMap;
		}

		public Map<Vertex, List<Vertex>> getAdjVertices() {
			return adjVertices;
		}
		
		public void addVertex(int label)
		{
			Vertex vertex = new Vertex(label);
			vertexMap.put(label, vertex);
			adjVertices.putIfAbsent(vertex, new ArrayList<>());
		}
		
		Vertex getVertex(int label)
		{
			return adjVertices.keySet().stream()
					.filter(key -> key.getLabel() == label).findFirst().orElseThrow(()-> new IllegalArgumentException("Vertex not found"));
		}
		
		void addEdge(int label1, int label2)
		{
			Vertex v1 = vertexMap.get(label1);
			Vertex v2 = vertexMap.get(label2);
			
			adjVertices.get(v1).add(v2);
			adjVertices.get(v2).add(v1);
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
					for(Vertex w: adjVertices.get(current))
					{
						if(!w.isVisited)
							stack.push(w);
					}
				}
			}
		}
		
		int DFSCC()
		{
			for(Vertex v: adjVertices.keySet())
			{
				v.isVisited=false;
				v.CCnum = 0;
			}
			int cc = 1;
			for(Vertex v : adjVertices.keySet())
			{
				if(!v.isVisited)
				{
					explore(v, cc);
					cc++;
				}
			}
			return cc -1 ;
		}
		
		public void printVerticesInfo() {
            for (Vertex v : adjVertices.keySet()) {
                System.out.println("Vertex label: " + v.getLabel() + "; CCnum: " + v.getCCnum() + "; isVisited: " + v.isVisited);
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
			this.isVisited = false;
			this.CCnum = 0;
		}

		public int getLabel() {
			return label;
		}

		public boolean isVisited() {
			return isVisited;
		}

		public int getCCnum() {
			return CCnum;
		}
	}

	public static class EasyScanner
	{
		public String nextLine()
		{
			Scanner s = new Scanner(System.in);
			String str = s.nextLine();
			return str;
		}
	}
}


