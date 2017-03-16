package ArraysAdHoc;

import java.util.ArrayList;

/**
 * This is the right solution
 *
 */
public class NextPalindrome {

    public static int nextPalindrome(int number) {

        int[] numArr = createDigitArray(number);

        if (all9s(numArr)) {
            return getNextPalindromeForAll9s(numArr);
        } else {

            int len = numArr.length;
            int mid = len / 2;

            boolean isEven = (len % 2) == 0;

            computeNextPalindrome(numArr, mid, isEven);
        }

        return arrayToNumber(numArr);
    }

    private static int getNextPalindromeForAll9s(int[] numArr) {
        int result[] = new int[numArr.length + 1];

        for (int i = 0; i < result.length; i++) {
            if (i == 0 || i == result.length - 1) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }
        }

        return arrayToNumber(result);

    }

    private static boolean all9s(int[] numArr) {
        for (int i : numArr) {
            if (i != 9) {
                return false;
            }
        }

        return true;
    }

    private static void computeNextPalindrome(int[] numArr, int midIndx, boolean isEven) {
        int left, right;

        // A bool variable to check if copy of left side to right is sufficient or not
        boolean isLeftSmaller = false;

        left = midIndx - 1;
        right = isEven ? midIndx : midIndx + 1;

        while (left >= 0 && numArr[left] == numArr[right]) {
            left--;
            right++;
        }

        if (left < 0 || numArr[left] < numArr[right]) {
            isLeftSmaller = true;
        }

        //mirror left side to right because the digit @ left index > digit @ right index ..
        //so safe to mirror to the right
        while (left >= 0) {
            numArr[right] = numArr[left];
            left--;
            right++;
        }

        //now left digit was smaller..so add 1 to digit at mid index
        //propagate carry to both sides
        if (isLeftSmaller) {
            int carry = 1;
            //re init left
            left = midIndx - 1;

            //increment mid index and compute carry
            if (!isEven) {
                numArr[midIndx] += carry;
                carry = numArr[midIndx] / 10;
                numArr[midIndx] = numArr[midIndx] % 10;
                right = midIndx + 1;
            } else {
                right = midIndx;
            }

            //carry forward the carry to MSD on the left, copying to the right simultaneously
            while (left >= 0) {
                numArr[left] += carry;
                carry = numArr[left] / 10;
                numArr[left] = numArr[left] % 10;
                numArr[right++] = numArr[left--];
            }
        }
    }

    private static int[] createDigitArray(int number) {
        ArrayList<Integer> digits = new ArrayList<>();

        int temp = number;
        //mod by 10 to get last digit and then divide by 10 to drop last digit
        while (temp > 0) {
            int digit = temp % 10;
            digits.add(digit);
            temp /= 10;
        }

        int[] finalNumArr = reverseNumArray(digits);

        return finalNumArr;
    }

    private static int[] reverseNumArray(ArrayList<Integer> target) {
        int[] resultArr = new int[target.size()];

        int len = target.size() - 1;
        int i = 0;
        //Reverse
        while (len >= 0) {
            resultArr[i] = (target.get(len));
            len--;
            i++;
        }

        return resultArr;
    }

    private static int arrayToNumber(int[] arr) {
        int number = 0;

        int digit = 0;
        for (int i = 0; i < arr.length; i++) {
            digit = arr[i];

            number += ((int) (Math.pow(10, i)) * digit);
        }

        System.out.println("arr to num " + number);

        return number;
    }

    public static void main(String args[]) {

        int res = nextPalindrome(999);
        System.out.print(res);

    }
}
