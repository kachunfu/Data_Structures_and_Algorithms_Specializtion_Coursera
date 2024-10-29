package isItABinarySearchTreeHardDSweek4Q3;

import java.util.Scanner;
import java.util.Stack;

public class App {
	
	static Node[] nodes;
	static BinaryTree bT;
	
	public static void main(String[] args) {
		
		EasyScanner es = new EasyScanner();
		System.out.println("Enter the number of nodes : ");
		int number = es.nextInt();
		bT = new BinaryTree();
		
		if(number!=0)
			nodes = new Node[number];
		
		for(int i = 0; i<number; i++)
			nodes[i] = new Node();
		
		for(int i = 0; i < number; i++)
		{
			if(i == 0)
				bT.root = nodes[i];
			System.out.println("For i = " + (i) + "; Enter the key of the node, index of left child and index of right child, seperated by space :");
			String str = es.nextLine();
			
			String[] sA = str.split(" ");
			long key = Long.parseLong(sA[0]);
			int leftI = Integer.parseInt(sA[1]);
			int rightI = Integer.parseInt(sA[2]);
			nodes[i].setKey(key);
			if(leftI != -1)
				nodes[i].setLeft(nodes[leftI]);
			else
				nodes[i].setLeft(null);
			if(rightI != -1)
				nodes[i].setRight(nodes[rightI]);
			else
				nodes[i].setRight(null);
		}
		bT.inOrderTraversalCheck();
		
	}
	
	public static class BinaryTree
	{
		Node root;
		
		void inOrderTraversalCheck()
		{
			if(root == null)
			{
				System.out.println("CORRECT");
				return;
			}
			
			Node current = root;
			Stack<Node> stack = new Stack<>();
			long parentK;
			
			while(current != null || !stack.isEmpty())
			{
				while(current != null)
				{
					parentK = current.getKey();
					stack.push(current);
					
					current = current.left;
					if(current!= null && current.getKey() >= parentK)
					{
						System.out.println("INCORRECT");
						return;
					}
				}
				current = stack.pop();
				parentK = current.getKey();
				
				current = current.right;
				if(current!= null && current.getKey() < parentK)
				{
					System.out.println("INCORRECT");
					return;
				}
					
			}
			System.out.println("CORRECT");
		}
		
	}
	
	public static class Node
	{
		long key;
		Node left;
		Node right;
		
		public Node()
		{
		}

		public long getKey() {
			return key;
		}

		public void setKey(long key) {
			this.key = key;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
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
		public int nextInt()
		{
			Scanner s = new Scanner(System.in);
			int i = s.nextInt();
			return i;
		}
		public long nextLong()
		{
			Scanner s = new Scanner(System.in);
			long l = s.nextLong();
			return l;
		}
	}
}
