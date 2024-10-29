package consistencyOfCSCurriculumHaveCycleW2Q2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Test3recursive {
	public static void main(String[] args) {
		
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
		
		Vertex getVertex(int label)
		{
			return adjVertices.keySet().stream()
					.filter(key -> key.label == label)
					.findFirst().orElseThrow(()-> new IllegalArgumentException("Vertex not found"));
		}
		
		void addEdge(int label1, int label2)
		{
			Vertex v1 = vertexMap.get(label1);
			Vertex v2 = vertexMap.get(label2);
			
			adjVertices.get(v1).add(v2);
			adjVertices.get(v2).add(v1);
		}
		
		
		void exploreRecursive(Vertex v, int CCnum, int clock)
		{
			isVisitedMap.put(v, true);
			previsit(v,clock);
			
			for(Vertex w: adjVertices.get(v))
			{
				if(!isVisitedMap.get(w))
					exploreRecursive(w, CCnum, clock);
			}
			postvisit(v,clock);
		}
		
		void DFS()
		{
			for(Vertex v: adjVertices.keySet())
			{
				isVisitedMap.put(v, false);
				CCnumMap.put(v, 0);
			}
			int cc = 1;
			int clock = 1;
			
			for(Vertex v : adjVertices.keySet())
			{
				if(!isVisitedMap.get(v))
				{
					exploreRecursive(v,cc, clock);
					cc++;
				}
			}
		}
		
		void previsit(Vertex v, int clock)
		{
			previsitMap.put(v, clock);
			clock ++;
		}
		
		void postvisit(Vertex v, int clock)
		{
			postvisitMap.put(v, clock);
			clock ++;
		}
		
	}
}
