package Graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a boolean 2D matrix, find the number of islands. A group of connected 1s forms an island.
 * For example, the below matrix contains 5 islands

Input : mat[][] = {{1, 1, 0, 0, 0},
                   {0, 1, 0, 0, 1},
                   {1, 0, 0, 1, 1},
                   {0, 0, 0, 0, 0},
                   {1, 0, 1, 0, 1} 
Output : 5
 * @author shalinishah
 *
 */
public class CountIslands {

	static class Cell {
		private int x, y;

		public Cell(int a, int b) {
			this.x = a;
			this.y = b;
		}
	}

	private static int ROW;
	private static int COL;
	
	public static List<Cell> getNeighbors(int[][] islandMatrix, int x, int y) {

		List<Cell> neighbor = new ArrayList<>();

		int xRange[] = { -1, -1, -1, 0, 1, 1, 1, 0 };
		int yRange[] = { -1, 0, 1, 1, 1, 0, -1, -1 };

		for (int i = 0; i < xRange.length; i++) {

			for (int j = 0; j < yRange.length; j++) {
				int x1 = x + xRange[i];
				int y1 = y + yRange[j];

				if (isValidCoord(x1, y1)) {
					if (islandMatrix[x1][y1] == 1) {
						neighbor.add(new Cell(x1, y1));
					}
				}
			}
		}

		return neighbor;

	}

	private static boolean isValidCoord(int x1, int y1) {

		if (x1 < 0 || x1 >= ROW || y1 < 0 || y1 >= COL) {
			return false;
		}

		return true;
	}

	public static int countIslands(int[][] matrix) {
		ROW = matrix.length;
		COL = matrix[0].length;
		
		int count = 0;
		boolean[][] visited = new boolean[ROW][COL];

		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {

				if (matrix[i][j] == 1 && !visited[i][j]) {
					exhaustComponent(matrix, i, j, visited);
					count++;
				}
			}
		}
		
		return count;
	}

	//DFS to exhaust all neighbors
	private static void exhaustComponent(int[][] matrix, int x, int y, boolean[][] visited) {
		
		ArrayList<Cell> neighbors  = (ArrayList<Cell>) getNeighbors(matrix, x, y);
		
		visited[x][y]= true;
		
		for(Cell c : neighbors){
			int x1 = c.x;
			int y1 = c.y;
			if(!visited[x1][y1]){
				exhaustComponent(matrix, x1, y1, visited);
			}
		}
		
	}
	
	public static void main(String args []){
	 int islandMatrix[][] = {{1, 1, 0, 0, 0},
             {0, 1, 0, 0, 1},
             {1, 0, 0, 1, 1},
             {0, 0, 0, 0, 0},
             {1, 0, 1, 0, 1}};
	 
	 int count = countIslands(islandMatrix);
	 
	 System.out.println(count);
	 
	int matrix[][] = {{1, 1},
             {0, 1},
             {1, 0},
             {0, 0},
             {1, 0}};
	 
	  count = countIslands(matrix);
	 
	 System.out.println(count);
	 }

}
