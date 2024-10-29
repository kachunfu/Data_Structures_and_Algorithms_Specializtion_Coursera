package buildingRoadsToConnectedCitiesW5Q2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Solution {
	
	public static void main(String[] args) {
		
		EasyScanner es = new EasyScanner();
		System.out.println("Enter number vertices :");
		int numV = es.nextInt();
		Graph graph = new Graph(numV);
		for(int i = 0; i< numV; i++)
		{
			System.out.println("Enter the x and y value of vertex " + (i+1) + ", separated by space :");
			String str = es.nextLint();
			String[] sA = str.split(" ");
			double x = Double.parseDouble(sA[0]);
			double y = Double.parseDouble(sA[1]);
			
			graph.vertices[i] = new Vertex(x,y);
		}
		graph.prim();
	}
	
	
	public static class Graph
	{
		public static class VertexCostPair
		{
			Vertex v;
			double cost;
			
			public VertexCostPair(Vertex v, double cost) {
				this.v = v;
				this.cost = cost;
			}
		}
		
		int sizeV;
		Vertex[] vertices;
		List<VertexCostPair> vC;
		Map<Vertex, Integer> vertexIndexMap;
		
		public Graph(int sizeV) 
		{
			this.sizeV = sizeV;
			this.vertices = new Vertex[sizeV];
			this.vertexIndexMap = new HashMap<>();
		}
		
		void prim()
		{
			vC = new LinkedList<>();
			double[] cost = new double[sizeV];
			Vertex[] parent = new Vertex[sizeV];
			
			for(int i = 0; i < vertices.length; i++)
			{
				VertexCostPair vcp = new VertexCostPair(vertices[i], Double.MAX_VALUE);
				cost[i] = vcp.cost;
				parent[i] = null;
				vC.add(vcp);
				vertexIndexMap.put(vertices[i], i);
			}
			
			int randomI = new Random().nextInt(vertices.length);
			cost[randomI] = 0;
			VertexCostPair vcp = vC.get(randomI);
			vcp.cost = 0;
			vC.set(randomI, vcp);
			buildHeap(vC);
			
			while(sizeV > 0)
			{
				VertexCostPair vertexCostPair = extractMin();
				Vertex vertexV = vertexCostPair.v;
				
				for(int i =0; i<vertices.length; i++)
				{
					if(!vertices[i].equals(vertexV))
					{
						double dist = Math.sqrt(Math.pow((vertices[i].x - vertexV.x), 2) + Math.pow((vertices[i].y - vertexV.y), 2));
						
						if(containsVertex(vC, vertices[i]) && cost[i] > dist)
						{
							cost[i] = dist;
							parent[i] = vertexV;
							changePriority(vertices[i], dist);
						}
					}
				}
			}
			double dist = 0;
			for(int i = 0; i< vertices.length; i++)
				dist+=cost[i];
			System.out.println(dist);
			return;
		}
		
		int parent(int i)
		{
			return (i-1)/2;
		}
		
		int left(int i)
		{
			return (i*2)+1;
		}
		
		int right(int i)
		{
			return (i*2)+2;
		}
		
		void swap(int i, int j)
		{
			VertexCostPair temp = vC.get(i);
			vC.set(i, vC.get(j));
			vC.set(j, temp);
			vertexIndexMap.put(vC.get(i).v, i);
			vertexIndexMap.put(vC.get(j).v, j);
		}
		
		void shiftUp(int i)
		{
			while(i>0 && vC.get(parent(i)).cost > vC.get(i).cost)
			{
				swap(parent(i), i);
				i = parent(i);
			}
		}
		
		void shiftDown(int i)
		{
			int minIndex = i;
			int left = left(i);
			int right = right(i);
			
			if(left <= sizeV-1 && vC.get(left).cost < vC.get(minIndex).cost)
				minIndex = left;
			if(right <= sizeV -1 && vC.get(right).cost < vC.get(minIndex).cost)
				minIndex = right;
			if(minIndex != i)
			{
				swap(i, minIndex);
				shiftDown(minIndex);
			}
		}
		
		VertexCostPair extractMin()
		{
			//pop head 
			VertexCostPair result = vC.get(0);
			
			//replace head with the last element
			vC.set(0, vC.get(sizeV-1));
			vertexIndexMap.put(vC.get(sizeV-1).v, 0);
			vC.remove(sizeV-1);
			
			sizeV = sizeV -1;
			shiftDown(0);
			vertexIndexMap.remove(result.v);
			return result;
		}
		
		void changePriority(Vertex vertex, double p)
		{
			int i = vertexIndexMap.get(vertex);
			double oldP = vC.get(i).cost;
			VertexCostPair vcp = new VertexCostPair(vertex, p);
			vC.set(i, vcp);
			if(oldP > p)
				shiftUp(i);
			else
				shiftDown(i);
		}
		
		void buildHeap(List<VertexCostPair> vC)
		{
			for(int i = vC.size()/2-1 ; i>=0; i--)
				shiftDown(i);
		}
		
		boolean containsVertex(List<VertexCostPair> vC, Vertex vertex)
		{
			for(VertexCostPair vcp: vC)
			{
				if(vcp.v.equals(vertex))
					return true;
			}
			return false;
		}
	}
	
	public static class Vertex
	{
		double x,y;

		public Vertex(double x, double y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
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
			return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
					&& Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
		}
		
	}
	public static class EasyScanner
	{
		String nextLint()
		{
			Scanner s = new Scanner(System.in);
			String str = s.nextLine();
			return str;
		}
		
		int nextInt()
		{
			Scanner s = new Scanner(System.in);
			int i = s.nextInt();
			return i;
		}
	}

}
