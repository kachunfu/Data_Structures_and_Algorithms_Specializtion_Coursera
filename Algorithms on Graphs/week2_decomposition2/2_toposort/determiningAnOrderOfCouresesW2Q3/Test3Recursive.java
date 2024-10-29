package determiningAnOrderOfCouresesW2Q3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import consistencyOfCSCurriculumHaveCycleW2Q2.Test3recursive.Vertex;

public class Test3Recursive {
	
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
		List<Map.Entry<Vertex, Integer>> sortedList = graph.quickSortByValueDescending(graph.postvisitMap);
		
		for(Map.Entry<Vertex, Integer> entry : sortedList)
		{
			System.out.print(entry.getKey().label + " ");
		}
		
		graph.printPostvisitMap();
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

	public static class Graph
	{
		Map<Integer, Vertex> vertexMap;
		Map<Vertex, List<Vertex>> adjVertices;
		Map<Vertex, Boolean> isVisitedMap;
		Map<Vertex, Integer> cCnumMap;
		Map<Vertex, Integer> previsitMap;
		Map<Vertex, Integer> postvisitMap;
		int clock = 1;
		
		public Graph() 
		{
			this.vertexMap = new HashMap<>();
			this.adjVertices = new HashMap<>();
			this.isVisitedMap = new HashMap<>();
			this.cCnumMap = new HashMap<>();
			this.previsitMap = new HashMap<>();
			this.postvisitMap = new HashMap<>();
		}

		public Map<Vertex, List<Vertex>> getAdjVertices() {
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
			previsitMap.entrySet().stream()
				.forEach(e-> System.out.println("Label : " + e.getKey().label + " ; value : " + e.getValue()));
		}
		
		void printPostvisitMap()
		{
			postvisitMap.entrySet().stream()
				.forEach(e -> System.out.println("Label : " + e.getKey().label + " ; value : " + e.getValue()));
		}
		
		Vertex getVertex(int label)
		{
			return adjVertices.keySet().stream()
					.filter(e -> e.label == label).findFirst()
					.orElseThrow(()-> new IllegalArgumentException("Vertex not found"));
		}
		
		void addEdge(int label1, int label2)
		{
			Vertex v1 = vertexMap.get(label1);
			Vertex v2 = vertexMap.get(label2);
			
			adjVertices.get(v1).add(v2);
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
					if(!isVisitedMap.get(current))
					{
						isVisitedMap.put(current, true);
						cCnumMap.put(current, cCnum);
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
							else if(postvisitMap.get(w) == null)
								throw new RuntimeException("This is not DAG");
						}
					}
				}
				else
					postvisit(current);
			}
		}
		
		void exploreRecursive(Vertex v, int CCnum)
		{
			isVisitedMap.put(v, true);
			previsit(v);
			
			for(Vertex w: adjVertices.get(v))
			{
				if(!isVisitedMap.get(w))
					exploreRecursive(w, CCnum);
			}
			postvisit(v);
		}
		
		void DFS()
		{
			for(Vertex v: adjVertices.keySet())
			{
				isVisitedMap.put(v, false);
				cCnumMap.put(v, -1);
			}
			int cc = 1;
			for(Vertex v : adjVertices.keySet())
			{
				if(!isVisitedMap.get(v))
				{
					exploreRecursive(v, cc);
					cc++;
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
			List<Map.Entry<Vertex, Integer>> entries = new ArrayList<>(map.entrySet());
			quickSort(entries, 0, entries.size()-1);
			return entries;
		}
		
		private static void quickSort(List<Map.Entry<Vertex, Integer>> entries, int left, int right)
		{
			if(left >= right || left < 0 )
				return;
			
			int pivotIndex = choosePivotIndex(left, right);
			Map.Entry<Vertex, Integer> pivot = entries.get(pivotIndex);
			int[] equalIndexPair = partition(entries, left, right, pivot);
			
			quickSort(entries, left, equalIndexPair[0] - 1);
			quickSort(entries, equalIndexPair[1] +1 , right);
			
			
		}
		
		private static int[] partition(List<Map.Entry<Vertex, Integer>> entries, int left, int right, Map.Entry<Vertex, Integer> pivot)
		{
			int leftIndex = left;
			int equalIndex = left;
			int rightIndex = right;
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
					{
						equalIndex++;
					}
				}
			}
			while(equalIndex <= rightIndex);
			
			equalIndexPair[0] = leftIndex;
			equalIndexPair[1] = rightIndex;
			
			return equalIndexPair;
		}
		
		private static int choosePivotIndex(int left, int right)
		{
			int pivotIndex = new Random().nextInt(right - left) + left;
			return pivotIndex;
		}
		
		private static void swap(List<Map.Entry<Vertex, Integer>> entries, int index1, int index2)
		{
			Map.Entry<Vertex, Integer> temp = entries.get(index1);
			entries.set(index1, entries.get(index2));
			entries.set(index2, temp);
			
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
