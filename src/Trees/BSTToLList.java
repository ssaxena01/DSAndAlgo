package Trees;

/**
 * Convert a BST to Circular LinkedList Convert a BST to a sorted circular
 * doubly-linked list in-place. Think of the left and right pointers as
 * synonymous to the previous and next pointers in a doubly-linked list.
 * 
 * @author shalinishah
 *
 */
public class BSTToLList {

	public static void treeToLL(Node root) {

		Node[] nodes = new Node[2];

		treeToCircularLL(root, nodes);

		Node temp = nodes[1];
		
		do{
			System.out.print(temp.val + " ");
			temp = temp.right;
		} while(temp != nodes[0]);

		System.out.print(temp.val + " ");
		

	}

	/**
	 * I need to modify the contract to take an array instead as Java is
	 *  pass by value and we cannot directly assign a reference. Instead I am
	 *  trying to cheat Java by passing an array and manipulating the array
	 * @param curr
	 * @param list
	 */
	private static void treeToCircularLL(Node curr, Node[] list) {

		if (curr == null) {
			return;
		}

		treeToCircularLL(curr.left, list);
		Node head = list[1];
		Node prev = list[0];
		
		curr.left = prev;
		
		if (prev != null) {
			prev.right = curr;
		} else {
			head = curr;
		}

		// save Right node of curr for recursive call on right subtree
		Node right = curr.right;

		head.left = curr;
		curr.right = head;

		// move prev to point to curr
		prev = curr;

		list[0] = prev;
		list[1] = head;
		
		treeToCircularLL(right, list);

	}

	public static void main(String[] args) {

		Node n1 = IsBST.createTree("335 -77 -81 # # 19 # # 547 505 350 # # # 807 692 # # 816 # # ");

		Node root1 = new Node(1);
		root1.left = new Node(2);
		root1.right = new Node(3);
		root1.left.left = new Node(4);
		// root1.left.right = new Node(5);
		// root1.right.left = new Node(6);
		// root1.right.right = new Node(7);

		// PrintPaths.printAllPaths(n1);

		// PostOrderIterative.postOrderIter(n1);

		treeToLL(n1);
		// Node curr = head.right;

		// do{
		// System.out.print(curr.val+",");
		// curr = curr.right;
		// } while(curr != head );
		// System.out.print(curr.val+",");
	}
}
