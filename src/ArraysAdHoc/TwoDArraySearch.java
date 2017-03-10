package ArraysAdHoc;
//http://articles.leetcode.com/searching-2d-sorted-matrix-part-ii
public class TwoDArraySearch {

	// search [N][M] array
	public static boolean search2DArray(int[][] array, int numberOfColumns, int numberOfRows, int target) {

		int M = numberOfColumns;
		int N = numberOfRows;
		// out of range
		if (target < array[0][0] || target > array[N - 1][M - 1]) {
			return false;
		}
		// start top right corner
		int row = 0;
		int col = M - 1;

		while (row <= N - 1 && col >= 0) {
			if (target < array[row][col]) {
				col--;
			} else if (target > array[row][col]) {
				row++;
			} else {
				return true;
			}
		}

		return false;
	}

	public static void main(String args[]) throws Exception {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT
		 */
		int[][] array = new int[3][3];

		array[0][0] = 1;
		array[0][1] = 4;
		array[0][2] = 7;

		array[1][0] = 2;
		array[1][1] = 5;
		array[1][2] = 8;

		array[2][0] = 3;
		array[2][1] = 6;
		array[2][2] = 9;

		boolean isFound = TwoDArraySearch.search2DArray(array, 3, 3, 2);

		System.out.println(isFound);

	}
}
