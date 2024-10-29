package consistencyOfCSCurriculumHaveCycleW2Q2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import findingAnExitFromAMazeW1Q2.App.EasyScanner;
import findingAnExitFromAMazeW1Q2.App.Graph;

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
		
		
		System.out.println(graph.DFSCycle());
//		graph.printPrevisitMap();
//		graph.printPostvisitMap();
		
		
	}

	
	public static class Vertex
	{
		int label;
		
		public Vertex(int label) {
			this.label = label;
		}
		
	}
	
	public static class Graph
	{
		Map<Integer, Vertex> vertexMap;
		Map<Vertex, List<Vertex>> adjVertices;
		Map<Vertex, Boolean> isVisitedMap;
		Map<Vertex, Integer> CCnumMap;
		Map<Vertex, Integer> previsitMap;
		Map<Vertex, Integer> postvisitMap;
		int clock = 1;
		
		public Graph() {
			this.vertexMap = new HashMap<>();
			this.adjVertices = new HashMap<>();
			this.isVisitedMap = new HashMap<>();
			this.CCnumMap = new HashMap<>();
			this.previsitMap = new HashMap<>();
			this.postvisitMap = new HashMap<>();
		}
		
		

		public Map<Vertex, List<Vertex>> getAdjVertices() 
		{
			return adjVertices;
		}
		
		void addVertex(int label)
		{
			Vertex v = new Vertex(label);
			vertexMap.put(label, v);
			adjVertices.putIfAbsent(v, new ArrayList<>());
		}
		
		void printPrevisitMap()
		{
			System.out.println("Previsit Map: ");
			previsitMap.entrySet().stream()
			.forEach(e-> System.out.println("Vertex  : " + e.getKey().label + " : " + e.getValue()));
		}
		
		void printPostvisitMap()
		{
			System.out.println("Postvisit Map: ");
			postvisitMap.entrySet().stream()
			.forEach(e-> System.out.println("Vertex  : " + e.getKey().label + " : " + e.getValue()));
		}
		
		Vertex getVertex(int label)
		{
			return adjVertices.keySet().stream()
					.filter(key -> key.label == label)
					.findFirst().orElseThrow(()-> new IllegalArgumentException("Vertex not found"));
		}
		
		
		//directional!!
		void addEdge(int label1, int label2)
		{
			Vertex v1 = vertexMap.get(label1);
			Vertex v2 = vertexMap.get(label2);
			
			adjVertices.get(v1).add(v2);
		}
		
		String exploreCycle(Vertex v, int CCnum)
		{
			Stack<Vertex>stack = new Stack<>();
			Stack<Boolean> visitFlag = new Stack<>();
			
			stack.push(v);
			visitFlag.push(true); // true indicates previsit, false indicates postvisit
			
			while(!stack.isEmpty())
			{
				Vertex current = stack.pop();
				boolean isPrevisit = visitFlag.pop();
				
				if(isPrevisit)
				{
					if(!isVisitedMap.get(current))
					{
						isVisitedMap.put(current, true);
						CCnumMap.put(current, CCnum);
						previsit(current);
						
						// Schedule postvisit for this node
						stack.push(current);
						visitFlag.push(false);
						
						for(Vertex w: adjVertices.get(current))
						{
							if(!isVisitedMap.get(w))
							{
								stack.push(w);
								visitFlag.push(true);
							}
							else if(postvisitMap.get(w)== null)
								return "1";
						}
					}
				}
				else
					postvisit(current);
			}
			return "0";
		}
		
		String DFSCycle()
		{
			for(Vertex v: adjVertices.keySet())
			{
				isVisitedMap.put(v, false);
				CCnumMap.put(v, 0);
			}
			int cc = 1;
			String TF = "";
			for(Vertex v : adjVertices.keySet())
			{
				if(!isVisitedMap.get(v))
				{
					TF = exploreCycle(v,cc);
					if(TF=="1")
						return "1";
					cc++;
				}
			}
			return TF;
		}
		
		void previsit(Vertex v)
		{
			previsitMap.put(v, clock);
			clock ++;
		}
		
		void postvisit(Vertex v)
		{
			postvisitMap.put(v, clock);
			clock ++;
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
