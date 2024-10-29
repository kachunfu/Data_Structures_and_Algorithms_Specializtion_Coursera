package detectingAnomaliesInCurrencyExchangeRatesW4Q3;

import java.util.Objects;
import java.util.Scanner;

public class Solution {
	
	public static void main(String[] args) {
		
		EasyScanner es = new EasyScanner();
		System.out.println("Enter the number of vertex and number of edges, separated by space");
		String ve = es.nextLine();
		String[] veA = ve.split(" ");
		int v = Integer.parseInt(veA[0]);
		int e = Integer.parseInt(veA[1]);
		
		Graph graph = new Graph(v,e);
		
		for(int i = 0; i < e; i++)
		{
			System.out.println("Enter two connected vertices and the distance, seperated by space: ");
			String vv = es.nextLine();
			String[] vvA = vv.split(" ");
			
			int v1 = Integer.parseInt(vvA[0]);
			int v2 = Integer.parseInt(vvA[1]);
			double d = Integer.parseInt(vvA[2]);
			
			graph.edges[i].source = graph.vertices[v1-1];
			graph.edges[i].dest = graph.vertices[v2-1];
			graph.edges[i].weight = d;
		}
		System.out.println(graph.checkNegativeCycleOnEveryVertex());
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
		public static class Edge
		{
			Vertex source;
			Vertex dest;
			double weight;
			
			public Edge() {
				this.source = null;
				this.dest = null;
				this.weight = 0;
			}
		}
		
		int v,e;
		Vertex[] vertices;
		Edge[] edges;
		
		public Graph(int v, int e) {
			this.v = v;
			this.e = e;
			this.vertices = new Vertex[v];
			this.edges = new Edge[e];
			
			for(int i = 0; i<v; i++)
			{
				vertices[i] = new Vertex(i+1);
			}
			
			for(int i = 0; i<e; i++)
			{
				edges[i] = new Edge();
			}
		}
		
		String checkNegativeCycleOnEveryVertex()
		{
			for(int i = 0; i<v; i++)
			{
				String ans = bellmanFordNegativeCycleCheck(i+1);
				if(ans=="1")
					return"1";
			}
			return "0";
		}
		
		String checkNegativeCycleOnEveryVertexTest()
		{
			String ans = bellmanFordNegativeCycleCheck(1);
			return ans;
		}

		String bellmanFordNegativeCycleCheck(int source)
		{
			double[] dist = new double[v];
			Vertex[] prev = new Vertex[v];
			
			for(int i=0; i<v; i++)
			{
				dist[i] = Double.MAX_VALUE;
				prev[i] = null;
			}
			dist[source-1] = 0;
			
			for(int i = 1; i< v; i++)
			{
				for(int j = 0; j<e; j++)
				{
					if(dist[edges[j].source.label -1] != Double.MAX_VALUE 
							&& dist[edges[j].dest.label -1] > dist[edges[j].source.label -1] + edges[j].weight)
					{
						dist[edges[j].dest.label-1] = dist[edges[j].source.label -1] + edges[j].weight;
						prev[edges[j].dest.label-1] = edges[j].source;
					}
				}
			}
			for(int j = 0; j<e; j++)
			{
				double distV =  dist[edges[j].dest.label-1];
				double distU = dist[edges[j].source.label-1];
				
				if(distU!=Double.MAX_VALUE && distV > distU + edges[j].weight)
					return"1";
			}
			return"0";
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
