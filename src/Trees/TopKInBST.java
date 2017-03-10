package Trees;

import java.util.LinkedList;
import java.util.Queue;

class Count{
	int count = 0;
}
public class TopKInBST {

 static	Node createBST(int [] iArray){
		
		int start = 0;
		int end = iArray.length;
		
		Node root  = createBSTFromArr(iArray, start, end-1);

		return root;
	}
	
	static Node createBSTFromArr(int[] arr, int st, int e){
		
		if(st > e){
			return null;
		}
		
		int mid =( st + e) / 2;
		
		Node root = new Node(arr[mid]);
		root.left = createBSTFromArr(arr, st, mid-1);
		root.right = createBSTFromArr(arr, mid+1, e);
		
		return root;
	}
	
	/**Print K smallest numbers in a BST
	 * 
	 * @param n
	 * @param array
	 * @param k
	 */
	static void printKSmallest(Node root, int k, Count c){
		
		if(root == null || c.count == k){
			return;
		}
		
		printKSmallest(root.left, k, c);
		System.out.println(root.val);
		c.count++;
		printKSmallest(root.right,k, c);
		
	}
	
	public static void main (String [] args){
		int[] arr = {7, 8, 10, 12, 15, 16, 20, 25};
		
		Node root = createBST(arr);
		printKSmallest(root, 4 , new Count());
		
		
	}
	
	private static void printLevelByLevel(Node n){
		if(n == null){
			return;
		}
		Queue<Node> q = new LinkedList<Node>();
		
		q.add(n);
		int level = q.size();
		
		while (!q.isEmpty()){
			level = q.size();
			
			while( level > 0){
				
				Node s = q.remove();
				System.out.print(s.val+" ");
				
				if(s.left != null){
					q.add(s.left);
				}
				
				if(s.right != null){
					q.add(s.right);
				}
				level--;
			}
			
			System.out.println(" ");
		}
	}

	private static void printInOrder(Node root, int [] arr, int index) {
		
		if(root == null){
			return;
		}
		
		printInOrder(root.left, arr, index);
		System.out.println(root.val);
		arr[index++] = root.val;
		printInOrder(root.right, arr, index);
	}
}
