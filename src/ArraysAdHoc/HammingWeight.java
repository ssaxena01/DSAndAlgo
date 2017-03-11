package ArraysAdHoc;

public class HammingWeight {

    private static int[] wordBitTable = new int[65536];

    public static void createLookupTable() {


        for (int i = 0; i < 0XFFFF; i++) {

            int num = i;
            int count = 0;
            // AND OF A NUMBER WITH (NUMBER-1) DROPS A 1 OFF
            // Number of iterations until Number = 0, gives count of 1's
            count = countBitsSet(num);

            // Store a count of that number in a Frequency Table
            wordBitTable[i] = count;
        }

    }

    private static int countBitsSet(int num) {
        int count = 0;
        while (num != 0) {
            num &= (num - 1);
            count++;
        }

        return count;
    }

    public static int onesCount(int val) {
        int count = 0;

        if (val > 65536) {
            count = countBitsSet(val);
        } else {
            count = wordBitTable[val];
        }

        return count;
    }

    public static int printCountOfBitsSet(int[] intArr) {

        createLookupTable();

        int count = 0;
        for (Integer x : intArr) {
            count += onesCount(x);
        }

        return count;
    }

    public static void main(String args[]) {


        int arr[] = {2147483647};

        int arr2[] = {6, 2, 232, 29, 1, 100, 0};


        System.out.println(printCountOfBitsSet(arr));

    }
}
