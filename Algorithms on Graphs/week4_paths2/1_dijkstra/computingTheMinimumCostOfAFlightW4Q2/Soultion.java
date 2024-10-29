package computingTheMinimumCostOfAFlightW4Q2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Soultion {
	

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
			System.out.println("Enter two connected vertices and the distance, seperated by space: ");
			String vv = es.nextLine();
			String[] vvA = vv.split(" ");
			
			int v1 = Integer.parseInt(vvA[0]);
			int v2 = Integer.parseInt(vvA[1]);
			int d = Integer.parseInt(vvA[2]);
			graph.addEdge(v1, v2, d);
		}
		
		System.out.println("Enter the start and end vertices, seperated by space: ");
		String vv = es.nextLine();
		String[] vvA = vv.split(" ");
		int v1 = Integer.parseInt(vvA[0]);
		int v2 = Integer.parseInt(vvA[1]);
		
		Vertex vertex1 = graph.getVertex(v1);
		
		int[] dist = graph.minCostDijkstra(vertex1);
		System.out.println(dist[v2]);
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
	
	public static class DistanceVertexPair
	{
		int distance;
		Vertex vertex;
		
		public DistanceVertexPair(int distance, Vertex vertex) 
		{
			this.distance = distance;
			this.vertex = vertex;
		}
	}
	
	public static class Graph
	{
		Map<Integer, Vertex> vertexMap;
		
		Map<Vertex, Map<Vertex, Integer>> adjVertices;
		
		List<DistanceVertexPair> pQ;
		
		int size;
		
		int[] minCostDijkstra(Vertex a)
		{
			size = vertexMap.entrySet().size();
			
			pQ = new LinkedList<>();
			
			Vertex[] prev = new Vertex[size+1];
			
			int[] dist = new int[size+1];
			
			for(Vertex u: adjVertices.keySet())
			{
				DistanceVertexPair dvp = new DistanceVertexPair(Integer.MAX_VALUE, u);
				dist[u.label] = dvp.distance;
				pQ.add(dvp);
//				pQ.set(u.label-1, dvp);
				prev[u.label] = null;
			}
			
			dist[a.label] = 0;
			DistanceVertexPair dvp = pQ.get(a.label-1);
			dvp.distance = 0;
			pQ.set(a.label-1, dvp);
			buildHeap(pQ);
			
			while(size >0)
			{
				DistanceVertexPair distanceVertexPair = extractMin();
				
				Vertex vertexU = distanceVertexPair.vertex;
				
				int distU = distanceVertexPair.distance;
				
				for(Vertex v: adjVertices.get(vertexU).keySet())
				{
					if(dist[v.label] > distU + adjVertices.get(vertexU).get(v))
					{
						dist[v.label] = distU + adjVertices.get(vertexU).get(v);
						prev[v.label] = vertexU;
						changePriority(v.label -1 ,dist[v.label]);
					}
				}
			}
			return dist;
		}
		
		int getMinCostPQ(int[] dist, int i)
		{
			return dist[i];
		}
		
		public Vertex getVertex(int label)
		{
			Vertex v = vertexMap.get(label);
			return v;
		}
		
		public Graph() {
			this.vertexMap = new HashMap<>();
			this.adjVertices = new HashMap<>();
			
		}

		public void addVertex(int label)
		{
			Vertex v = new Vertex(label);
			vertexMap.put(label, v);
			adjVertices.put(v, new HashMap<>());
		}
		
		public void addEdge(int label1, int label2, int distance)
		{
			Vertex v1 = getVertex(label1);
			Vertex v2 = getVertex(label2);
			adjVertices.get(v1).put(v2, distance);
		}
		int parent(int i)
		{
			return (i-1)/2;
		}
		
		int left(int i)
		{
			return 2*i+1;
		}
		
		int right(int i)
		{
			return 2*i + 2;
		}
		
		void swap(int i, int j)
		{
			DistanceVertexPair temp = pQ.get(i);
			pQ.set(i, pQ.get(j));
			pQ.set(j, temp);
		}
		
		void shiftUp(int i)
		{
			while(i>0 && pQ.get(parent(i)).distance > pQ.get(i).distance)
			{
				swap(parent(i), i);
				i = parent(i);
			}
		}
		
		void shiftDown(int i)
		{
			//parent
			int minIndex = i;
			
			int left = left(i);
			
			if(left <= size-1 && pQ.get(left).distance < pQ.get(minIndex).distance)
				minIndex = left;
			
			int right = right(i);
			
			if(right <= size-1 && pQ.get(right).distance < pQ.get(minIndex).distance)
				minIndex = right;
			
			if(i != minIndex)
			{
				swap(i, minIndex);
				shiftDown(minIndex);
			}
		}
		
		DistanceVertexPair extractMin()
		{
			//pop head
			DistanceVertexPair result = pQ.get(0);
			//replace head with the last element
			pQ.set(0, pQ.get(size-1));
			size = size -1;
			shiftDown(0);
			return result;
		}
		
		void changePriority(int i, int p)
		{
			int oldp = pQ.get(i).distance;
			DistanceVertexPair dvp = new DistanceVertexPair(p, pQ.get(i).vertex);
			pQ.set(i, dvp);
			if(p < oldp)
				shiftUp(i);
			else
				shiftDown(i);
		}
		
		void buildHeap(List<DistanceVertexPair> pQ)
		{
			for(int i = pQ.size()/2 ; i>= 0; i--)
				shiftDown(i);
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
