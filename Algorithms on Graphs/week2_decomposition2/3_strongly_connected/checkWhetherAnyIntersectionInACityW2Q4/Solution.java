package checkWhetherAnyIntersectionInACityW2Q4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import determiningAnOrderOfCouresesW2Q3.Solution.EasyScanner;
import determiningAnOrderOfCouresesW2Q3.Solution.Graph;
import determiningAnOrderOfCouresesW2Q3.Solution.Vertex;

public class Solution {

	public static void main(String[] args) {
		
		EasyScanner es = new EasyScanner();
		System.out.println("Enter number of vertices and number of edges, seperated by space ");
		String ve = es.nextLine();
		String[] veA = ve.split(" ");
		int v = Integer.parseInt(veA[0]);
		int e = Integer.parseInt(veA[1]);
		
		Graph graph = new Graph();
		Graph graphR = new Graph();
		
		for(int i = 0; i< v; i++)
		{
			graph.addVertex(i+1);
			graphR.addVertex(i + 1);
		}
		
		for(int i =0; i<e ; i++)
		{
			System.out.println("Enter two connected vertices, seperated by space: ");
			String vv = es.nextLine();
			String[] vvA = vv.split(" ");
			
			int v1 = Integer.parseInt(vvA[0]);
			int v2 = Integer.parseInt(vvA[1]);
			graph.addEdge(v1, v2);
			graphR.addEdge(v2, v1);
		}
		System.out.println(graph.numberOfSCCs(graphR));
	}
	
	public static class Graph
	{
		Map<Integer, Vertex> vertexMap;
		Map<Vertex, List<Vertex>> adjVertices;
		Map<Vertex, Integer> cCnumMap;
		Map<Vertex, Boolean> isVisitedMap;
		Map<Vertex, Integer> previsitMap;
		Map<Vertex, Integer> postvisitMap;
		Map<Integer, List<Vertex>> sCCsMap;
		
		int clock = 1;
		
		public Graph() 
		{
			this.vertexMap = new HashMap<>();
			this.adjVertices = new HashMap<>();
			this.cCnumMap = new HashMap<>();
			this.isVisitedMap = new HashMap<>();
			this.previsitMap = new HashMap<>();
			this.postvisitMap = new HashMap<>();
			this.sCCsMap = new HashMap<>();
		}
		
		void printPrevisitMap()
		{
			previsitMap.entrySet()
			.stream()
			.forEach(e -> System.out.println("Label : " + e.getKey().label + " ; value : " + e.getValue()));
		}
		void printPostvisitMap()
		{
			postvisitMap.entrySet()
			.stream()
			.forEach(e -> System.out.println("Label : " + e.getKey().label + " ; value : " + e.getValue()));
		}
		
		void addVertex(int label)
		{
			Vertex v = new Vertex(label);
			vertexMap.put(label, v);
			adjVertices.put(v, new ArrayList<>());
		}
		
		Vertex getVertex(int label)
		{
			return adjVertices.keySet().stream()
					.filter(e -> e.label == label)
					.findFirst().orElseThrow(()-> new IllegalArgumentException("Vertex not found"));
		}
		
		void addEdge(int label1, int label2)
		{
			Vertex v1 = vertexMap.get(label1);
			Vertex v2 = vertexMap.get(label2);
			
			adjVertices.get(v1).add(v2);
		}
		
		int numberOfSCCs(Graph graphR)
		{
			graphR.DFS();
			List<Map.Entry<Vertex, Integer>> sortedListR = graphR.quickSortByValueDescending(graphR.postvisitMap);
			List<Vertex> list = getVerticesFromSortedEntries(sortedListR);
			
			for(Vertex v: list)
			{
				isVisitedMap.put(v, false);
				cCnumMap.put(v, -1);
			}
			int cc = 1;
			
			for(Vertex v : list)
			{
				if(!isVisitedMap.get(v))
				{
					explore(v, cc);
					cc++;
				}
			}
			
			return sCCsMap.keySet().size();
		}
		
		public static List<Vertex> getVerticesFromSortedEntries(List<Map.Entry<Vertex, Integer>> sortedList)
		{
			List<Vertex> vertices = new ArrayList<>();
			for(Map.Entry<Vertex, Integer> entry : sortedList)
				vertices.add(entry.getKey());
			return vertices;
		}
		
		void explore(Vertex v, int cCnum)
		{
			Stack<Vertex> stack = new Stack<>();
			Stack<Boolean> visitFlag = new Stack<>();
			
			stack.push(v);
			visitFlag.push(true);
			
			while(!stack.isEmpty()) 
			{
				Vertex current = stack.pop();
				boolean isPrevisit = visitFlag.pop();
				
				if(isPrevisit)
				{
					if(! isVisitedMap.get(current))
					{
						isVisitedMap.put(current, true);
						cCnumMap.put(current, cCnum);
						
						if(!sCCsMap.containsKey(cCnum))
							sCCsMap.put(cCnum, new ArrayList<>());
						
						sCCsMap.get(cCnum).add(current);
						
						previsit(current);
						
						stack.push(current);
						visitFlag.push(false);
						
						for(Vertex w: adjVertices.get(current))
						{
							if(!isVisitedMap.get(w))
							{
								stack.push(w);
								visitFlag.push(true);
							}
						}
					}
				}
				else
					postvisit(current);
			}
		}
		
		void DFS()
		{
			for(Vertex v: adjVertices.keySet())
			{
				isVisitedMap.put(v, false);
				cCnumMap.put(v, -1);
			}
			int cCnum = 1;
			for(Vertex v: adjVertices.keySet())
			{
				if(!isVisitedMap.get(v))
				{
					explore(v, cCnum);
					cCnum++;
				}
			}
		}
		
		void previsit(Vertex v)
		{
			previsitMap.put(v, clock);
			clock++;
		}
		void postvisit(Vertex v)
		{
			postvisitMap.put(v, clock);
			clock++;
		}
		
		public List<Map.Entry<Vertex, Integer>> quickSortByValueDescending(Map<Vertex, Integer> map)
		{
			List<Map.Entry<Vertex,Integer>> entries = new ArrayList<>(map.entrySet());
			quickSort(entries, 0 , entries.size()-1);
			return entries;
		}
		
		private static void quickSort(List<Map.Entry<Vertex, Integer>> entries, int left, int right)
		{
			if(left >= right || left < 0)
				return;
			
			int pivotIndex = choosePivotIndex(left,right);
			Map.Entry<Vertex, Integer> pivot = entries.get(pivotIndex);
			int[] equalIndexPair = partition(entries, left, right, pivot);
			quickSort(entries, left, equalIndexPair[0] -1);
			quickSort(entries, equalIndexPair[1] + 1, right);
		}
		
		private static int[] partition(List<Map.Entry<Vertex, Integer>> entries, int left, int right, Map.Entry<Vertex, Integer> pivot)
		{
			int leftIndex = left;
			int equalIndex = left;
			int rightIndex =right;
			int[] equalIndexPair = new int[2];
			do
			{
				if(entries.get(equalIndex).getValue()> pivot.getValue())
				{
					swap(entries, equalIndex, leftIndex);
					leftIndex++;
					equalIndex++;
				}
				else if(entries.get(equalIndex).getValue() < pivot.getValue())
				{
					swap(entries, equalIndex, rightIndex);
					rightIndex--;
				}
				else
				{
					if(entries.get(equalIndex).getValue().equals(pivot.getValue()))
						equalIndex++;
				}
			}
			while(equalIndex<=rightIndex);
			
			equalIndexPair[0] = leftIndex;
			equalIndexPair[1] = rightIndex;
			
			return equalIndexPair;
		}
		
		private static int choosePivotIndex(int left, int right)
		{
			int pivotIndex = new Random().nextInt(right - left) + left;
			return pivotIndex;
		}
		
		private static void swap(List<Map.Entry<Vertex, Integer>> entries, int index1 , int index2)
		{
			Map.Entry<Vertex, Integer> temp = entries.get(index1);
			entries.set(index1,  entries.get(index2));
			entries.set(index2, temp);
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
