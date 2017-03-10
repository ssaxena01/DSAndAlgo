package ArraysAdHoc;

/**
 * Given an array of numbers, nums, return an array of numbers products, where
 * products[i] is the product of all nums[j], j != i.
 * 
 * Input : [1, 2, 3, 4, 5] Output: [(2*3*4*5), (1*3*4*5), (1*2*4*5), (1*2*3*5),
 * (1*2*3*4)] = [120, 60, 40, 30, 24] You must do this in O(N) without using
 * division. Solution:
 * http://stackoverflow.com/questions/2680548/given-an-array-of-numbers-return-
 * array-of-products-of-all-other-numbers-no-div
 * 
 *
 */

public class ArraysProduct {

	public static int[] computeProducts(int[] input) {

		int size = input.length;
		int products[] = new int[size];

		int prod = 1;
		int[] product_left = new int[size];

		for (int i = 0; i < size; i++) {
			product_left[i] = prod;
			System.out.println("left[ " + i + "]= " + product_left[i]) ;
			prod *= input[i];
			System.out.println("prod " + "= " + prod) ;
		}

		prod = 1;
		int[] product_right = new int[size];

		for (int j = size - 1; j >= 0; --j) {
			product_right[j] = prod;
			System.out.println("right [" + j + "]= " + product_right[j]) ;
			prod *= input[j];
			System.out.println("prod " + "= " + prod) ;
		}

		for (int i = 0; i < size; i++) {
			products[i] = product_right[i] * product_left[i];
		}

		return products;
	}

	public static void main(String args[]) throws Exception {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT
		 */

		int[] arr = { 8, 2, 3, 4, 5 };
		int[] products = ArraysProduct.computeProducts(arr);

		for (int i = 0; i < products.length; i++) {
			System.out.print(products[i] + "\n");
		}
	}

}
