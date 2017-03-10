	package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * (This is an interview twist on a classical CS problem)
 * 
 * Assume you're given a normal chessboard and a knight that moves like in
 * normal chess. You are then given two inputs: starting location and ending
 * location in the form of x,y co-ordinates. The goal is to calculate the
 * shortest number of moves that the knight can take to get to the target
 * location.
 * 
 * Input: (All the coordinates start with 0 and end with (rows-1) or (cols -1)).
 * For a 8x8 board the first cell will be (0,0) and the corresponding opposite
 * corner cell will be (7,7)
 *
 */
public class KnightsTour2 {

	static int ROW;
	static int COL;

	static class Cell {
		int x;
		int y;

		public Cell(int a, int b) {
			super();
			this.x = a;
			this.y = b;
		}

		List<Cell> neighbors = new ArrayList<>();

		/**
		 * Key method that determines given a position of the knight all other
		 * cells it can move to
		 * 
		 * 8 possible neighbors e.g.: (i+2, j-1), (i+2, j+1), (i-2, j+1), (i-2,
		 * j-1),
		 * 
		 * @return
		 */
		List<Cell> getNeighbors() {
			int[] range = { -2, -1, 1, 2 };
			int x1, y1;
			for (int i = 0; i < range.length; i++) {
				for (int j = 0; j < range.length; j++) {
					// for any possible neighbor notice, i and j will never
					// change by same value
					// for e.g. (i-1, j-1).
					if (Math.abs(range[i]) == Math.abs(range[j])) {
						continue;
					}

					// is outside the chessboard range
					x1 = x + range[i];
					y1 = y + range[j];
					if (x1 < 0 || x1 >= ROW || y1 < 0 || y1 >= COL) {
						continue;
					}

					neighbors.add(new Cell(x1, y1));
				}
			}

			return neighbors;

		}
		
		@Override
		public int hashCode() {
			final int prime = 13;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cell other = (Cell) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

	}
	
	static class Vertex{
		Cell cell;
		//parent
		Cell backRef;
		
		public Vertex(Cell c, Cell backRef){
			this.cell = c;
			this.backRef = backRef;
		}
	}

	static int minimumMoves(int rows, int cols, int startx, int starty, int endx, int endy) {
		ROW = rows;
		COL = cols;
		Cell start = new Cell(startx, starty);
		Cell end  = new Cell(endx, endy);
		int minMoves = findShortestPath(start, end);
		
		return minMoves;
	
	}

	//BFS
	private static int findShortestPath(Cell start, Cell end) {
		Queue<Vertex> q = new LinkedList<>();
		HashSet<Cell> visited = new HashSet<>();
		HashMap<Cell, Cell> backRefs = new HashMap<>(); // cell and it's backref mapping
		
		q.add(new Vertex(start, null));
		
		while(!q.isEmpty()){
			Vertex v = q.remove();
			
			Cell current = v.cell;
			
			if(visited.contains(current)){
				continue;
			}
			
			//keep track of nodes/cells I'm visiting along with it's parent (how I got there
			backRefs.put(v.cell, v.backRef);
			visited.add(current);
			
			if(current.equals(end)){
				break;
			}
			
			for(Cell neighbor : current.getNeighbors()){
				q.add(new Vertex(neighbor, current));
			}
			
		}
		
		Cell temp = end;
		int pathLen = 0;
		
		while(temp != start){
			pathLen++;
			temp = backRefs.get(temp);
		}
		
		return pathLen;
	}
	
	public static void main(String [] args){
		int moves = minimumMoves(16, 16, 0, 7, 3,4);
		
		System.out.println(moves);
	}
	
}