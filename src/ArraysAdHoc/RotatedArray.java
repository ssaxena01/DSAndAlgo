package ArraysAdHoc;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.You may assume no duplicate exists in the array.
 * 
 * @author shalinishah
 *
 */

public class RotatedArray {

	public static int findMin(int array[]) {
		return findMin(array, 0, array.length - 1);
	}
	
	 static int findMin1(int arr[], int low, int high)
	    {
	        // This condition is needed to handle the case when array
	        // is not rotated at all
	        if (high < low)  return arr[0];
	 
	        // If there is only one element left
	        if (high == low) return arr[low];
	 
	        // Find mid
	        int mid = low + (high - low)/2; /*(low + high)/2;*/
	 
	        // Check if element (mid+1) is minimum element. Consider
	        // the cases like {3, 4, 5, 1, 2}
	        if (mid < high && arr[mid+1] < arr[mid])
	            return arr[mid+1];
	 
	        // Check if mid itself is minimum element
	        if (mid > low && arr[mid] < arr[mid - 1])
	            return arr[mid];
	 
	        // Decide whether we need to go to left half or right half
	        if (arr[high] > arr[mid])
	            return findMin(arr, low, mid-1);
	        return findMin(arr, mid+1, high);
	    }

	private static int findMin(int[] array, int start, int end) {
		// array not rotated.

		// if indices are same ie 1 element only
		if (start == end) {
			return array[start];
		}

		// only 2 elements
		if (end - start == 1) {
			return Math.min(array[start], array[end]);
		}

		int mid = start + (end - start) / 2;
		int leftMost = array[start];

		if (array[start] < array[end]) {
			return array[start];
		}

		if (array[mid] < leftMost) {

			return findMin(array, start, mid);

		} else {

			return findMin(array, mid, end);

		}

	}

	public static void main(String args[]) {
		int[] arr = { 5, 4, 5, 1, 2, 3 };
		int[] arr2 = { 10, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8 };

		int min = findMin(arr2);

		System.out.println(min);

		/*
		 * int [] arr2 = {7, 10}; System.out.println(findMin(arr2));
		 */
	}

}
