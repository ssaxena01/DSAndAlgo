package TestAdHoc;

public class TwoSumProblem {

	public static int[] twoSum(int[] array, int target) {
		int[] out = new int[2];

		int[] compArr = new int[array.length];

		for (int i = 0; i < array.length; i++) {
			if (array[i] >= target) {
				compArr[i] = -1;
				continue;
			}

			compArr[i] = target - array[i];
		}

		for (int x = 0; x < array.length; x++) {

			for (int y = 0; y < array.length; y++) {

				if (compArr[x] == -1) {
					continue;
				}

				if (compArr[x] == array[y]) {
					if (x < y) {
						out[0] = x;
						out[1] = y;
					} else {
						out[1] = x;
						out[0] = y;
					}
					break;
				}
			}
		}
		return out;

	}

	public static void main(String[] args) {
		int[] arr = { 5, 8, 16, 3, 12, 1 };
		int tar = 13;

		int[] out = twoSum(arr, tar);

		System.out.println(out[0] + " , " + out[1]);
	}
}
