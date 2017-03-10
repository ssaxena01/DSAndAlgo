package Trees;

import java.util.Stack;



/**
 * PostOrder traversal without recursion
 * 
 * @author shalinishah
 *
 */
public class PostOrderIterative {

	static void postOrderIter(Node root) {

		Stack<Node> s = new Stack<Node>();
		s.push(root);

		while (!s.isEmpty()) {
			Node curr = s.peek();
			if (curr == null) {
				break;
			}
			if (curr.left == null && curr.right == null) {
				// leaf node
				System.out.print(curr.val + ", ");
				s.pop();

			} else {
				if (curr.right != null) {

					s.push(curr.right);
					curr.right = null;
				}
				if (curr.left != null) {
					s.push(curr.left);
					curr.left = null;
				}
			}
		}

	}
	
	public static void main(String [] args){
		String str1 = "7 5 # 6 # # 94 89 75 69 53 43 21 # # # # # 88 # # #";
		Node n1 = MergeTwoTrees.createTree(str1);
		
		Node root1 = new Node(1);
		root1.left = new Node(2);
		root1.right = new Node(3);
		root1.left.left = new Node(4);
		root1.left.right = new Node(5);
		root1.right.left = new Node(6);
		root1.right.right = new Node(7);
		
		MergeTwoTrees.printInOrder(root1);
		postOrderIter(root1);
	}
}
