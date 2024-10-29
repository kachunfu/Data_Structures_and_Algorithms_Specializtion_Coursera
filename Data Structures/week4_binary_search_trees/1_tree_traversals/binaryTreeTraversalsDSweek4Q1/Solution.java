package binaryTreeTraversalsDSweek4Q1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

//Tree as a class: 

public class Solution {
	
	static Node[] nodes;
	static BinaryTree bT;

	public static void main(String[] args) 
	{
		EasyScanner es = new EasyScanner();
		System.out.println("Enter number of node in tree : ");
		int number = es.nextInt();
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
			if(iLeft == -1)
				nodes[i].setLeft(null);
			else
				nodes[i].setLeft(nodes[iLeft]);
			if(iRight == -1)
				nodes[i].setRight(null);
			else
				nodes[i].setRight(nodes[iRight]);
			
			if(i==0)
			{
				bT = new BinaryTree();
				bT.root= nodes[0];
			}
		}
		
		bT.inOrderTraversal();
		System.out.println();
		bT.preOrderTraversal();
		System.out.println();
		bT.postOrderTraversal();
	}
	
	public static class Node
	{
		int key;
		Node left;
		Node right;
		
		public Node() 
		{
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

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}
		
	}
	
	public static class BinaryTree
	{
		Node root;
		
		//depth first
		void inOrderTraversal()
		{
			if(root == null)
				return;
			
			Stack<Node> stack = new Stack<>();
			Node current = root;
			
			while(current != null || ! stack.isEmpty())
			{
				//reach the leftmost node of the current subtree
				while(current != null)
				{
					stack.push(current);
					current = current.left;
				}
				//Current is null at this point, pop from the stack
				current = stack.pop();
				
				System.out.print(current.getKey() + " ");
				
				current = current.right;
				
			}
		}
		//depth first
		void preOrderTraversal()
		{
			if(root == null)
				return;
			Node current = root;
			Stack<Node> stack = new Stack<>();
			while(current != null || ! stack.isEmpty())
			{
				while(current != null)
				{
					System.out.print(current.getKey() + " ");
					stack.push(current);
					current = current.left;
				}
				current = stack.pop();
				
				while(current != null && current.right == null && ! stack.isEmpty())
				{
					current = stack.pop();
				}
				current = current.right;
			}
		}
		
		//depth first
		void postOrderTraversal()
		{
			if(root == null)
				return;
			Node current = root;
			Stack<Node> stack = new Stack<>();
			Node lastVisitedNode = null;
			
			while(current != null || !stack.isEmpty())
			{
				while(current != null)
				{
					stack.push(current);
					current = current.left;
				}
				Node peekNode = stack.peek();
				if(peekNode.getRight()!= null && peekNode.getRight() != lastVisitedNode)
					current = peekNode.getRight();
				else
				{
					Node poppedNode = stack.pop();
					System.out.print(poppedNode.getKey() + " ");
					lastVisitedNode = poppedNode;
				}
			}
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
