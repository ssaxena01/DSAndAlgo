package Graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class TopologicalSort {

	private int totalVertices;
	private LinkedList<Integer> neighbors[];

	public TopologicalSort(int v) {
		totalVertices = v;

		neighbors =  new LinkedList [totalVertices];
		for (int i = 0; i < totalVertices; i++) {
			neighbors[i] = new LinkedList<Integer>();
		}
	}

	void addEdge(int v, int w) {
		neighbors[v].add(w);
	}

	void topoSort() {
		Stack<Integer> s = new Stack<>();

		boolean visited[] = new boolean[totalVertices];

		for (int i = 0; i < totalVertices; i++) {
			if(!visited[i]){
				topoSortHelper(i, visited, s);
			}
		}
		
		while(!s.isEmpty()){
			System.out.print(s.pop() + " , ");
		}
	}

	private void topoSortHelper(int v, boolean[] visited, Stack<Integer> s) {
		
		//mark this node visited
		visited[v] = true;
		Integer x;
		
		Iterator<Integer> itr = neighbors[v].iterator();
		while(itr.hasNext()){
			//get neighbor
			x = itr.next();
			//is visited?
			if(!visited[x]){
				topoSortHelper(x, visited, s);
			}
		}
		
		//all neighbors exhausted..add to stack
		s.push(new Integer(v));
	}
	
	public static void main(String[] args){
		 // Create a graph given in the above diagram
        TopologicalSort g = new TopologicalSort(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
 
        System.out.println("Following is a Topological " +
                           "sort of the given graph");
        g.topoSort();
	}
}
