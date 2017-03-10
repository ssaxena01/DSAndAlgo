package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CloneGraph {

	static class Vertex {
		private int val;
		private List<Vertex> neighbors;

		public Vertex(int val) {
			this.val = val;
			this.neighbors = new ArrayList<Vertex>();
		}

		public void addEdge(Vertex v) {
			neighbors.add(v);
		}
	}

	// use BFS to clone graph
	public static Vertex cloneGraph(Vertex vertex) {
		HashMap<Vertex, Vertex> cloneMap = new HashMap<>();
		HashSet<Vertex> visited = new HashSet<>();

		Queue<Vertex> q = new LinkedList<Vertex>();
		q.add(vertex);
		Vertex copy = new Vertex(vertex.val);
		cloneMap.put(vertex, copy);

		while (!q.isEmpty()) {
			Vertex v = q.poll();
			Vertex vClone = cloneMap.get(v);

			if (visited.contains(v)) {
				continue;
			}

			if (v.neighbors != null) {
				for (Vertex n : v.neighbors) {
					q.add(n);

					// check if this neighbor was cloned?
					Vertex nClone = cloneMap.get(n);

					// not cloned
					if (nClone == null) {
						nClone = new Vertex(n.val);
						cloneMap.put(n, nClone);
					}

					vClone.neighbors.add(nClone);
				}
			}
			
			visited.add(v);
		}

		// return clone of source Vertex
		return cloneMap.get(vertex);

	}

	public static void bfsTraverse(Vertex src) {

		Queue<Vertex> q = new LinkedList<>();
		HashSet<Vertex> visited = new HashSet<>();

		q.add(src);

		while (!q.isEmpty()) {
			Vertex v = q.poll(); // poll will NOT throw exception

			if (visited.contains(v)) {
				continue;
			}
			System.out.println("vertex val -> " + v.val);
			System.out.println("vertex add -> " + v);

			if (v.neighbors != null) {
				for (Vertex n : v.neighbors) {
					q.add(n);
				}
			}

			visited.add(v);

		}
	}

	// Build the desired graph
	public static Vertex buildGraph() {
		/*
		 * Note : All the edges are Undirected Given Graph: 1--2 | | 4--3
		 */
		Vertex node1 = new Vertex(1);
		Vertex node2 = new Vertex(2);
		Vertex node3 = new Vertex(3);
		Vertex node4 = new Vertex(4);
		ArrayList<Vertex> v = new ArrayList<Vertex>();
		v.add(node2);
		v.add(node4);
		node1.neighbors = v;
		v = new ArrayList<Vertex>();
		v.add(node1);
		v.add(node3);
		node2.neighbors = v;
		v = new ArrayList<Vertex>();
		v.add(node2);
		v.add(node4);
		node3.neighbors = v;
		v = new ArrayList<Vertex>();
		v.add(node3);
		v.add(node1);
		node4.neighbors = v;
		return node1;
	}

	public static void main(String args[]) {

		Vertex source = buildGraph();
		bfsTraverse(source);

		Vertex clone = cloneGraph(source);
		bfsTraverse(clone);
	}

}
