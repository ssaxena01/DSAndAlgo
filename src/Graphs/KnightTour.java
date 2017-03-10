package Graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*
 * THIS CODE ISN'T GIVING RIGHT ANSWERS.
 */
class Vertex {
	int x, y;
	// min distance from source
	int distFromSrc = 0;

	public Vertex(int a, int b, int d) {
		this.x = a;
		this.y = b;
		this.distFromSrc = d;
	}

	public Vertex(int a, int b) {
		this.x = a;
		this.y = b;
	}

}

public class KnightTour {

	static int boardSize = 0;

	// possible 8 movements of the knight on a chess board
	static int[] row = { 2, 2, -2, -2, 1, -1, -1, 1 };
	static int[] col = { -1, 1, 1, -1, -2, -2, 2, 2 };

	static HashMap<Vertex, Boolean> visited = new HashMap<>();
	
	static void setBoardSize(int size){
		boardSize = size;
	}

	// check if a cell is valid , knight cannot go outside the chess board
	static public boolean isValidMove(int x, int y) {
		if (x < 0 || y < 0 || x >= boardSize || y >= boardSize) {
			return false;
		}

		return true;
	}

	// find min number of moves for a knight to reach a destination
	static int findMinMoves(int totRow, int totCol, int startx, int starty, int endx, int endy) {

		Queue<Vertex> q = new LinkedList<Vertex>();
		Vertex start = new Vertex(startx, starty);
		visited.put(start,false);

		q.add(start);

		while (!q.isEmpty()) {
			Vertex v = q.remove();
			int move = v.distFromSrc;
			int x = v.x;
			int y = v.y;

			if (x == endx && y == endy) {
				return move;
			}

			if (visited.containsKey(v) && !visited.get(v)) {
				// not visited, mark visited
				visited.put(v, true);

				// check it's 8 neighbors for possible moves
				for (int i = 0; i < 8; i++) {

					int x1 = x + row[i];
					int y1 = y + col[i];

					if (isValidMove(x1, y1)) {
						Vertex neighbor = new Vertex(x1, y1, move+1);
						q.add(neighbor);
//						System.out.println(x1 + "," + y1 + " q size " + q.size() + "  " +neighbor);
						visited.put(neighbor, true);
					}

				}

			}
		}

		return -1;
	}

	public static void main(String [] args){
//		int move = findMinMoves(0, 4, 2, 4, 8, 8);
		
//		System.out.println(move);
		
		setBoardSize(8);
		System.out.println(findMinMoves(8, 8, 0, 7, 3, 4));
	}
}
