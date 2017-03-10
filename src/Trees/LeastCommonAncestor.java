package Trees;

import java.util.*;
/** this solution is for a Binary tree. The tree may or mnot be BST
 * 
 */

/**
 * Sample Inputs from hackerrank -- 71 
 * 221 96 84 235 53 # # 75 # # # 17 77 16 10
 * # # 70 # # 74 # # # 216 214 34 142 # # 188 # # 219 181 86 44 124 # # 98 54 #
 * # 99 # # # # 79 240 130 137 # # 3 63 158 # # 123 # # # # 106 # # 237 # # 
 * 10
 * 74
 * 
 * testcase 2 -- 77 193 12 72 203 # # 116 231 # # 59 # # 74 110 213 56 184 21 55 66
 * 197 # # 69 222 # # 92 # # # # 234 # # # # # 34 39 48 44 200 33 78 # # 242 # #
 * # # 63 # # # # 181 250 245 # # 117 4 151 # # 135 # # 194 # # 9 # # 234 9
 *
 * #3  --- 67 
 * 92 219 124 111 53 183 # # 14 184 132 63 45 123 180 208 # # 228 # # # #
 * # # # # # 223 # # 15 182 195 # # 71 154 # # 179 # # # 193 65 213 246 # # 68
 * 41 # # 2 47 227 # # 49 # # # 144 # # #
 * 47 219
 * 
 * #4 -- 31 
 * 85 # 116 188 130 31 # # 44 212 # # 148 235 # # 13 # # 233 66 164 # # 64
 * # # # 141 # # 
 * 235 64
 * 
 * 
 */

public class LeastCommonAncestor {

	static Node getLCA(Node root, int x, int y) {
		// int lca = Integer.MIN_VALUE;

		List<Node> path1 = new ArrayList<>();
		List<Node> path2 = new ArrayList<>();

		path1 = findPath(root, x, path1);
		path2 = findPath(root, y, path2);

		for (int j = 0; j < path1.size(); j++) {
			System.out.println(path1.get(j));
		}

		System.out.println("***");

		for (int j = 0; j < path2.size(); j++) {
			System.out.println(path2.get(j));
		}

		int i = 0;
		for (; i < path1.size() && i < path2.size(); i++) {
			if (path1.get(i).val != path2.get(i).val) {
				break;
			}
		}

		// lca = path1.get(i-1).val;

		return path1.get(i - 1);
	}

	private static List<Node> findPath(Node root, int value, List<Node> path) {
		if (root == null) {
			return null;
		}

		path.add(root);

		if (root.val == value) {
			// found
			return path;
		}

		List<Node> leftPath = (findPath(root.left, value, new ArrayList<>(path)));
		List<Node> rightPath = (findPath(root.right, value, new ArrayList<>(path)));

		if (leftPath != null && isValueInPath(value, leftPath)) {
			return leftPath;
		}

		if (rightPath != null && isValueInPath(value, rightPath)) {
			return rightPath;
		}

		path.remove(path.size() - 1);
		return path;
	}

	private static boolean isValueInPath(int value, List<Node> path) {
		for (Node n : path) {
			if (n.val == value) {
				return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {

		Node root = new Node(45);
		root.left = new Node(25);
		root.right = new Node(65);
		root.left.left = new Node(15);
		root.left.right = new Node(30);
		root.left.left.left = new Node(10);
		root.left.left.right = new Node(20);

		root.right.left = new Node(55);
		root.right.right = new Node(75);
		root.right.left.left = new Node(50);
		root.right.left.right = new Node(60);
		root.right.right.right = new Node(80);

		System.out.println("LCA for 10, 20 = " + getLCA(root, 10, 20).val);
		System.out.println("LCA for 50, 80 = " + getLCA(root, 50, 80).val);
		System.out.println("LCA for 30, 55 = " + getLCA(root, 30, 55).val);
	}

}
