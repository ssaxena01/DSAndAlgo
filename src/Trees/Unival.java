package Trees;


public class Unival {
	static class Count {
		int count = 0;
	}

	static Count counter = new Count();

	static int findSingleValueTrees(Node n) {

		findSingleValueTrees(n, counter);

		return counter.count;

	}

	private static boolean findSingleValueTrees(Node node, Count c) {

		if (node == null) {
			return true;
		}

		boolean left = findSingleValueTrees(node.left, c);
		boolean right = findSingleValueTrees(node.right, c);

		// this block of code is also correct. here we  check for node.left.val != values
		/*if(!left || !right){
			return false;
		}
		
		if(node.left != null && node.left.val != node.val){
			return false;
		}
		
		if(node.right != null && node.right.val != node.val){
			return false;
		}
		
		c.count++;
		
		return true;*/
		
		// this block is also correct. Here we check for node.left.val == value
		if (left && right) {

			if (node.left == null && node.right == null) {
				c.count++;
				return true;
			}

			if (node.right != null && node.right.val == node.val && node.left != null && node.left.val == node.val) {
				c.count++;
				return true;
			}

			if (node.left != null && node.left.val == node.val) {
				c.count++;
				return true;
			}

			if (node.right != null && node.right.val == node.val) {
				c.count++;
				return true;
			}

		}

		return false;

	}

	public static void main(String[] args) {

		Node root = new Node(4);
		root.left = new Node(4);
		root.right = new Node(4);
		root.left.left = new Node(4);
		root.left.right = new Node(4);
		root.left.left.left = new Node(4);
		root.left.left.right = new Node(4);

		Node r2 = new Node(5);
		r2.left = new Node(5);
		r2.left.left = new Node(5);
		r2.left.right = new Node(5);
		r2.right = new Node(5);
		r2.right.right = new Node(5);

		System.out.println(findSingleValueTrees(r2));
		
		counter.count = 0;
		System.out.println(findSingleValueTrees(root));

		
		
	}

}
