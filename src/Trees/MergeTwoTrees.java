package Trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class MergeTwoTrees {

	static Node createTree(String data) {
		if (data == null || data.length() == 0) {
			return null;
		}

		StringTokenizer st = new StringTokenizer(data, " ");

		return desearlize(st);
	}

	private static Node desearlize(StringTokenizer st) {

		if (!st.hasMoreTokens()) {
			return null;
		}

		String s = st.nextToken();

		if (s.equals("#")) {
			return null;
		}

		Node root = new Node(Integer.valueOf(s));
		root.left = desearlize(st);
		root.right = desearlize(st);

		return root;
	}

	static Node mergeTrees(Node n1, Node n2) {
		ArrayList<Integer> tree1 = new ArrayList<>();
		putInOrderArray(n1, tree1, 0);

		ArrayList<Integer> tree2 = new ArrayList<>();
		putInOrderArray(n2, tree2, 0);

		tree1.addAll(tree2);
		Collections.sort(tree1);

		Node root = createBST(tree1, 0, tree1.size() - 1);
		printInOrder(root);
		System.out.print("\n");
		printLevelByLevel(root);
		return root;
	}

	static void putInOrderArray(Node n, ArrayList<Integer> arr, int index) {

		if (n == null) {
			return;
		}
		putInOrderArray(n.left, arr, index);
		arr.add(index++, n.val);
		putInOrderArray(n.right, arr, index);
	}

	static Node createBST(ArrayList<Integer> arr, int start, int end) {

		if (start > end) {
			return null;
		}
		int mid = (start + end) / 2;
		Node root = new Node(arr.get(mid));
		root.left = createBST(arr, start, mid - 1);
		root.right = createBST(arr, mid + 1, end);

		return root;
	}

	static void printLevelByLevel(Node root) {
		if (root == null) {
			return;
		}
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		int levelSize = q.size();

		while (!q.isEmpty()) {
			levelSize = q.size();
			
			while (levelSize > 0) {
				Node node = q.remove();
				System.out.print(node.val + " ");

				if (node.left != null) {
					q.add(node.left);
				}

				if (node.right != null) {
					q.add(node.right);
				}
				
				levelSize--;
			}
			System.out.println();
		}
	}

	public static void printInOrder(Node root) {
		if (root == null) {
			return;
		}

		printInOrder(root.left);
		System.out.print(root.val + " ");
		printInOrder(root.right);
	}

	public static void main(String[] args) {
		String str1 = "7 5 # 6 # # 94 89 75 69 53 43 21 # # # # # 88 # # #";
		Node n1 = createTree(str1);

		String str2 = "62 42 2 # 17 # 32 25 # # # 47 # # 86 63 # 70 # 82 77 73 # # # # 99 87 # # #";
		Node n2 = createTree(str2);

		Node root = mergeTrees(n1, n2);
		
		Node root1 = new Node(1);
		root1.left = new Node(2);
		root1.right = new Node(3);
		root1.left.left = new Node(4);
		root1.left.right = new Node(5);
		root1.right.left = new Node(6);
		root1.right.right = new Node(7);

		printLevelByLevel(root1);
		
		printInOrder(root1);
	}

}
