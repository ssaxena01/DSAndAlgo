package ArraysAdHoc;

/**
 * Given an array of positive and negative numbers, arrange them in an
 * alternate fashion such that every positive number is followed by negative
 * and vice-versa maintaining the order of appearance.
 * Number of positive and negative numbers need not be equal.
 * If there are more positive numbers they appear at the end of the array.
 * If there are more negative numbers, they too appear in the end of the array.
 */
public class ArrangePosNev {

    /* We'll keep track of a number that may be out of place
    * A right in place number is:
    * Even Index has -ve number
    * Odd index has +ve number
    * Converse will be out of place
    *
    * */

    public static void reArrange(int[] input) {
        int outOfPlace = -1;

        for (int i = 0; i < input.length; i++) {

            //we've detected something out of place
            if (outOfPlace >= 0) {

                //we'll try to rotate the array in that case
                if ((input[i] >= 0 && input[outOfPlace] < 0) ||
                       input[i] < 0 && input[outOfPlace] >=0 ){
                    rotateToRight(input, i, outOfPlace);


                     // the new out-of-place entry is now 2 steps ahead
                    if (i - outOfPlace > 2)
                        outOfPlace  += 2;
                    else
                        outOfPlace = -1;

                }
            }

            if(outOfPlace == -1){
                // +ve at even loc OR -ve at odd loc
                if((input[i] >=0 && (i & 0x01) == 0 ) ||
                        input[i] < 0 && (i & 0X01) == 1){
                    outOfPlace = i;
                }

            }
        }
    }

    private static void rotateToRight(int[] input, int currIndx, int outOfPlace) {

        int temp = input[currIndx];

        for(int j = currIndx; j > outOfPlace; j--) {
            input[j] = input[j-1];
        }
        input[outOfPlace] = temp;
    }

    // A utility function to print an array 'arr[]' of size 'n'
    public static void printArray(int arr[], int n) {
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
        System.out.println("");
    }


    public static void main(String args[]){
        int arr[] = {-5, -3, -4, -5, -6, -2 , 8, 9, 1 , 4, 90};
        System.out.println("Given array is ");
        printArray(arr, arr.length);

        reArrange(arr);

        System.out.println("RearrangeD array is ");
        printArray(arr, arr.length);
    }

}


