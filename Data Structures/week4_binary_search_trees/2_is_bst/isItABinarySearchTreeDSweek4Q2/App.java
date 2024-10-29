package isItABinarySearchTreeDSweek4Q2;

import java.util.Scanner;
import java.util.Stack;

public class App {
	
	static Node[] nodes;
	static BinaryTree bT ;
	
	
	public static void main(String[] args) 
	{
		
		EasyScanner es = new EasyScanner();
		System.out.println("Enter number of node in tree : ");
		int number = es.nextInt();
		
		bT = new BinaryTree();
		
		if(number!= 0)
			nodes = new Node[number];
		
		for(int i = 0; i <  number ; i++)
			nodes[i] = new Node();
		
		for(int i = 0; i <  number ; i++)
		{
			System.out.println("For i = " + (i) + "; Enter the key of the node, index of left child and index of right child, seperated by space :");
			String s = es.nextLine();
			
			String[] sA = s.split(" ");
			int key = Integer.parseInt(sA[0]);
			int iLeft = Integer.parseInt(sA[1]);
			int iRight = Integer.parseInt(sA[2]);
				
			nodes[i].setKey(key);
			if(i == 0)
				nodes[i].setParent(null);
			if(iLeft == -1)
				nodes[i].setLeft(null);
			else
			{
				nodes[i].setLeft(nodes[iLeft]);
				nodes[iLeft].setParent(nodes[i]);
			}
			if(iRight == -1)
				nodes[i].setRight(null);
			else
			{
				nodes[i].setRight(nodes[iRight]);
				nodes[iRight].setParent(nodes[iRight]);
			}
			if(i==0)
			{
				bT.root= nodes[0];
			}
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
			int max = Integer.MIN_VALUE;
			Stack<Node> stack = new Stack<>();
			while(current != null || ! stack.isEmpty())
			{
				while(current != null)
				{
					stack.push(current);
					current = current.left;
				}
				current = stack.pop();
				if(current.getKey() > max)
				{
					max = current.getKey();
//					System.out.println(max);
				}
				else
				{
					System.out.println("INCORRECT");
					return;
				}
				current = current.getRight();
			}
			System.out.println("CORRECT");
		}
	}
	
	public static class Node
	{
		int key;
		Node left;
		Node right;
		Node parent;
		
		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public Node()
		{
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
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
		public static String nextLine()
		{
			Scanner s = new Scanner(System.in);
			String str = s.nextLine();
			return str;
		}
		public static int nextInt()
		{
			Scanner s = new Scanner(System.in);
			int i = s.nextInt();
			return i;
		}
	}

}
