package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * You have a dictionary of words and two strings a and b. How can one convert a
 * to b by changing only one character at a time and making sure that all the
 * intermediate words are in the dictionary? Example:
 * 
 * dictionary: {"cat", "bat", "hat", "bad", "had"} a = "bat" b = "had" solution:
 * 
 * "bat" -> "bad" -> "had"
 * 
 * @author shalinishah
 *
 */
public class AToB {

	private HashSet<Vertex> vertices = new HashSet<>();
	private HashMap<String, Vertex> map = new HashMap();

	static class Vertex {
		private String word;
		private Vertex parent = null;

		private List<Vertex> neighbors = new ArrayList<>();

		public Vertex(String w) {
			word = w;
		}

		public void addEdge(Vertex v) {
			neighbors.add(v);
		}
	}

	public void buildGraph(Set<String> dict) {

		for (String s : dict) {
			Vertex v = new Vertex(s);
			vertices.add(v);
			map.put(s, v);
		}

		// add edges
		addEdgesInGraph(dict);
	}

	private void addEdgesInGraph(Set<String> dict) {
		Iterator<String> itr = dict.iterator();
		while (itr.hasNext()) {
			String val = itr.next();
			Vertex v = map.get(val);

			Iterator<String> iter2 = dict.iterator();

			while (iter2.hasNext()) {
				String val2 = iter2.next();
				if (val2.equals(val) || val2.length() != val.length()) {
					continue;
				}

				if (isDifferentBySingleChar(val.toCharArray(), val2.toCharArray())) {
					// add edge
					Vertex neighbor = map.get(val2);
					v.addEdge(neighbor);
					
				}
			}
		}
	}

	private boolean isDifferentBySingleChar(char[] arr1, char[] arr2) {
		
		int count = 0;

		for (int i = 0; i < arr1.length; i++) {
			
			if (arr1[i] != arr2[i]) {
				count++;
				if (count > 1) {
					return false;
				}
			}
		}

		
		return true;
	}

	private void findPath(String s1, String s2, Set<String> dict) {

		buildGraph(dict);

		// search String
		List<String> path = findPath(s1, s2);

		for(int j = path.size() - 1; j >=0; j--){
		
			String s = path.get(j);
			
			System.out.print(s);
			if(j!= 0){
				System.out.print("->");
			}
		}
		
	}
// BFS
	private List<String> findPath(String s1, String s2) {
		Vertex start = map.get(s1);
		Vertex end = map.get(s2);
		HashSet<Vertex> visited = new HashSet<Vertex>();
		HashMap<Vertex, Vertex> backRefs = new HashMap<>();

		backRefs.put(start, null);

		Queue<Vertex> q = new LinkedList<Vertex>();

		q.add(start);

		while (!q.isEmpty()) {
			Vertex v = q.remove();

			if (visited.contains(v)) {
				continue;
			}

			visited.add(v);
			backRefs.put(v, v.parent);
			
			if (v.equals(end)) {
				break;
			}

			for (Vertex neighbor : v.neighbors) {
				neighbor.parent = v;				
				q.add(neighbor);
			}

		}

		Vertex prev = end;

		List<String> path = new ArrayList<>();
		path.add(prev.word);

		while (prev != start) {
			prev = backRefs.get(prev);
			if (prev != null) {
				System.out.println("word " + prev.word);
				path.add(prev.word);
			}
		}

		return path;
	}

	public static void main(String args[]) {
		AToB graph = new AToB();

		String dict[] = { "bat", "car", "had", "hat", "bad" };

		Set<String> set = new HashSet<>();

		for (String s : dict) {
			set.add(s);
		}

		graph.findPath("bat", "had", set);
	}
}
