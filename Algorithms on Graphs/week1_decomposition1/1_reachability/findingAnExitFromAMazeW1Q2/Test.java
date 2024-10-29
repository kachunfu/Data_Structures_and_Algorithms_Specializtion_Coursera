package findingAnExitFromAMazeW1Q2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Stack;

public class Test {

	public static void main(String[] args) {
		
	
		
	}
	
	public static class Graph
	{
		Map<Vertex, List<Vertex>> adjVertices;
		
		public Graph(int edges) {
			this.adjVertices = new HashMap<Vertex, List<Vertex>>();
		}



		public Map<Vertex, List<Vertex>> getAdjVertices() {
			return adjVertices;
		}



		public void setAdjVertices(Map<Vertex, List<Vertex>> adjVertices) {
			this.adjVertices = adjVertices;
		}



		void addVertex(int label)
		{
			adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
		}
		
		void removeVertex(int label)
		{
			Vertex v = new Vertex(label);
			adjVertices.values().stream().forEach(e -> e.remove(v));
			adjVertices.remove(new Vertex(label));
		}
		
		void addEdge(int label1, int label2)
		{
			Vertex v1 = new Vertex(label1);
			Vertex v2 = new Vertex(label2);
				
			adjVertices.get(v1).add(v2);
			adjVertices.get(v2).add(v1);
		}
		
		//recursive
//		void explore(Vertex v)
//		{
//			v.isVisited = true;
//			
//			for(Vertex w : adjVertices.get(v))
//			{
//				if(!w.isVisited)
//					explore(w);
//			}
//		}
		String checkConnectiveity(int label1, int label2)
		{
			Optional<Vertex> v1 = adjVertices.keySet().stream()
								.filter(key -> key.getLabel()==label1).findFirst();
			
			Optional<Vertex> v2 = adjVertices.keySet().stream()
								.filter(key -> key.getLabel()==label2).findFirst();
			
			if(v1.get().CCnum == v2.get().CCnum)
				return "1";
			else
				return "0";
			
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
					explore(v, cc);
				cc++;
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
}
