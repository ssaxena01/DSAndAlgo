package ArraysAdHoc;

import java.util.Scanner;

/**
 * Pascal’s triangle is a triangular array of the binomial coefficients. Write a
 * function that takes an integer value n as input and prints first n lines of
 * the Pascal’s triangle. Following are the first 6 rows of Pascal’s Triangle. 
 * 1
 * 1 1 
 * 1 2 1 
 * 1 3 3 1
 * 1 4 6 4 1 
 * 1 5 10 10 5 1
 *
 */
public class PascalTriangle {

	public static void pascalsTriangle(int n) {

		int[][] array = new int[n+1][n+1];

		for (int row = 1; row <= n; row++) {

			for (int column = 1; column <= row; column++) {
				if (column == row || column == 1) {
					array[row][column] = 1;
					System.out.print(array[row][column] + "\t") ;
					continue;
				}

				array[row][column] = array[row - 1][column - 1] + array[row - 1][column];
				System.out.print(array[row][column] + "\t");
			}
			System.out.println("\n");
		}

	}
	
	public static void main(String [] args){
		
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		
		PascalTriangle.pascalsTriangle(num);
	}

}
