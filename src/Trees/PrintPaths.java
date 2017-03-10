package Trees;

import java.util.ArrayList;

/**
 * Print all paths from root-to-leaf in a Binary Tree using recursion
 * 
 * @author shalinishah
 *
 */
public class PrintPaths {

	static void printAllPaths(Node root) {

		ArrayList<Node> list = new ArrayList<>();
		printPaths(root, list);
	}

	static void printPaths(Node node, ArrayList<Node> list) {

		if (node == null) {
			return;
		}

		list.add(node);

		if (node.left == null && node.right == null) {
			// leaf
			printArray(list);
		}

		printPaths(node.left, new ArrayList<>(list));

		printPaths(node.right, new ArrayList<>(list));

	}

	private static void printArray(ArrayList<Node> list) {

		for (Node n : list) {
			System.out.print(n.val + " ");
		}
		System.out.println();

	}

	public static void main(String[] args) {
		Node root1 = new Node(1);
		root1.left = new Node(2);
		root1.right = new Node(3);
		root1.left.left = new Node(4);
		root1.left.right = new Node(5);
		root1.right.left = new Node(6);
		root1.right.right = new Node(7);

		printAllPaths(root1);

	}
}
