package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class AlienLangTopologicalSort {

	static class Vertex {
		private char c;
		private List<Vertex> edges = new ArrayList<>();

		public Vertex(char c) {
			super();
			this.c = c;
		}
	}

	public static void main(String args[]) {

		String words[] = { "caa", "aaa", "aab" };

		String order = findOrder(words);

		System.out.println(order);

	}

	static String findOrder(String[] strDict) {

		HashMap<Character, Vertex> charToVertex = new HashMap<>();

		// build a graph
		buildAGraph(strDict, charToVertex);

		String order = topologicalSort(charToVertex);

		return order;
	}

	private static void buildAGraph(String[] words, HashMap<Character, Vertex> charToVertex) {
		// set up vertices
		for (String word : words) {
			char[] cArr = word.toCharArray();

			for (Character c : cArr) {
				if (!charToVertex.containsKey(c)) {
					charToVertex.put(c, new Vertex(c));
				}
			}
		}

		// add edges between from and to
		for (int i = 0; i < words.length - 1; i++) {

			char[] from = words[i].toCharArray();
			char[] to = words[i + 1].toCharArray();

			for (int j = 0; j < Math.min(from.length, to.length); j++) {

				if (from[j] != to[j]) {
					// create edge
					Vertex v = charToVertex.get(from[j]);
					Vertex neighbor = charToVertex.get(to[j]);
					v.edges.add(neighbor);
					break;
				}
			}
		}

	}

	private static String topologicalSort(HashMap<Character, Vertex> charToVertex) {
		Stack<Vertex> s = new Stack<>();
		HashSet<Vertex> visited = new HashSet<>();

		for (Vertex v : charToVertex.values()) {
			if (!visited.contains(v)) {
				dfs(v, visited, s);
			}
		}

		StringBuffer order = new StringBuffer();
		while (!s.isEmpty()) {
			char c = s.pop().c;

			order.append(c);

		}

		return order.toString();
	}

	private static void dfs(Vertex v, HashSet<Vertex> visited, Stack<Vertex> stack) {
		if (visited.contains(v)) {
			System.out.println("Got a cycle");
			return;
		}
		visited.add(v);
		for (Vertex n : v.edges) {
			dfs(n, visited, stack);
		}

		stack.push(v);

	}
}
