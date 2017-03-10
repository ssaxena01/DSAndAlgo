package Trees;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Given a binary tree, find out if it's a BST.
 * 
 * @author shalinishah
 *
 */
public class IsBST {

//	static class Node{
//		int val;
//		Node left;
//		Node right;
//		
//		public Node(int val){
//			this.val = val;
//		}
//	}
	
	static 	Node createTree(String data){
		if(data == null || data.length() == 0){
			return null;
		}
		
		StringTokenizer st = new StringTokenizer(data, " ");
		
		return desearlize(st);
	}
	
	  private static Node desearlize(StringTokenizer st) {
		
		  if(!st.hasMoreTokens()){
			  return null;
		  }
		  
		  String s = st.nextToken();
		  
		  if(s.equals("#")){
			  return null;
		  }
		  
		  Node root = new Node(Integer.valueOf(s));
		  root.left = desearlize(st);
		  root.right = desearlize(st);
		  
		return root;
	}

	static boolean isBST(Node root) {
	        
	       return isBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	    }

	    static boolean isBSTHelper(Node parent, int low, int high){
	        
	        if(parent == null){
	            return true;
	        }
	        
	        if( (parent.val > low) && (parent.val < high) 
	          && (isBSTHelper(parent.left, low, parent.val))
	          &&(isBSTHelper(parent.right, parent.val, high))){
	            return true;
	        }
	        
	        
	        return false;
	    }
	    
	    public static void main(String [] args) throws IOException{
	    	Scanner s = new Scanner(System.in);	
	    	final String path = System.getenv("OUTPUT_PATH");
	    		
		    	BufferedWriter bw = new BufferedWriter(new FileWriter(path));
	    	
	    	boolean res;
	    	int _size = Integer.parseInt(s.nextLine().trim());
	    	
	    	String _str = null;
	    	
	    	try{
	    		_str = s.nextLine();
	    		
	    	} catch(Exception e){
	    		e.printStackTrace();	
	    	}
	    	
	    	Node n = createTree(_str);
	    	res = isBST(n);
	    	bw.write(String.valueOf(res ? 1 : 0));
	    	bw.newLine();
	    	
	    	bw.close();
	  
	    }
}
