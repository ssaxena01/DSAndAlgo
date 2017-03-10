package DynamicProgramming;

public class EditDistance {

	// A Naive recursive Java program to find minimum number
	// operations to convert str1 to str2

	static int min(int x, int y, int z) {
		int min1 = Math.min(x, y);
		return Math.min(min1, z);

	}

	static int editDist(String str1, String str2, int m, int n) {
		// If first string is empty, the only option is to
		// insert all characters of second string into first
		if (m == 0)
			return n;

		// If second string is empty, the only option is to
		// remove all characters of first string
		if (n == 0)
			return m;

		// If last characters of two strings are same, nothing
		// much to do. Ignore last characters and get count for
		// remaining strings.
		if (str1.charAt(m - 1) == str2.charAt(n - 1))
			return editDist(str1, str2, m - 1, n - 1);

		// If last characters are not same, consider all three
		// operations on last character of first string, recursively
		// compute minimum cost for all three operations and take
		// minimum of three values.
		return 1 + min(editDist(str1, str2, m, n - 1), // Insert
				editDist(str1, str2, m - 1, n), // Remove
				editDist(str1, str2, m - 1, n - 1) // Replace
		);
	}

	// A Dynamic Programming based Java program to find minimum
	// number operations to convert str1 to str2
	static int editDistDP(char[] str1, char[] str2, int len1, int len2) {

		int[][] tempDp = new int[len1 + 1][len2 + 1];

		// init row 0 as every operation to convert to a null string will be
		// that number of characters
		// MUST INITIALIZE TO LENGTH OF ROW 0. IF INITIALIZED TO LEN OF STRING
		// 1..IF string 1 = 1 char, this algo wont work

		for (int i = 0; i < tempDp[0].length; i++) {

			tempDp[0][i] = i;
		}

		// init column 0 for all rows ; to convert a null string to target
		// string will take # of characters in target str
		for (int j = 0; j < tempDp.length; j++) {
			tempDp[j][0] = j;
		}

		// start with 1st column and row of matrix
		for (int i = 1; i <=len1; i++) {
			for (int j = 1; j <= len2; j++) {

				if (str1[i - 1] == str2[j - 1]) { // char array still indexed
													// from 0
					tempDp[i][j] = tempDp[i - 1][j - 1]; // select diagonal
															// value from matrix
															// if 2 char are
															// same
				} else {
					// get min of the 3 numbers, to my left, diagonal to me and
					// above me in the matrix
					tempDp[i][j] = 1 + min(tempDp[i][j - 1], tempDp[i - 1][j - 1], tempDp[i - 1][j]);
				}

			}
		}

		return tempDp[len1][len2];
	}

	public static void main(String args[]) {
		String str1 = "abc";
		String str2 = "cxy";

		System.out.println(editDistDP(str1.toCharArray(), str2.toCharArray(), str1.length(), str2.length()));
		// System.out.println( editDist( str1 , str2 , str1.length(),
		// str2.length()) );
	}
}
