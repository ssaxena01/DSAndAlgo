package ArraysAdHoc;

/**
 * Created by shalinishah on 3/14/17.
 */
public class NextPalindrome {


    /**
     * Mirror the number such that lower half digits same as higher half of digits
     * For example, the mirror of 65432 is 65456
     *
     * @param n
     * @return
     */
    int mirror(int n)
    {
        String num = String.valueOf(n);
        char[] arr = num.toCharArray();
        int mid = arr.length/2;
        int i= (arr.length%2==0)?mid:mid+1;
        // copy higher half of digits to lower half
        while(i<arr.length) {
            arr[i]=arr[mid-1];
            mid--;
            i++;
        }
        num=String.valueOf(arr);
        return Integer.valueOf(num);
    }

    int incrementFromMiddle(int n)
    {
        String num = String.valueOf(n);
        char[] arr = num.toCharArray();
        int midpoint = arr.length/2;
        int currPoint=arr.length%2==0?midpoint-1:midpoint;
        boolean found = false;

        while (currPoint >= 0 && !found) {
            char c = arr[currPoint];
            char inc;
            if (c == '9') {
                inc = '0';
            }
            else {
                inc = (char) (c + 1);
                found = true;
            }
            arr[currPoint] = inc;
            if (!found) {
                currPoint--;
            }
        }

        if (!found) {
            // we have fallen off the start of the string
            // example 999 has become 009. Add a one on to give: 1009
            final char[] newarr = new char[arr.length + 1];
            newarr[0] = '1';
            System.arraycopy(arr, 0, newarr, 1, arr.length);
            num = new String(newarr);
            return Integer.valueOf(num);
        }
        else {
            num = new String(arr);
            return Integer.valueOf(num);
        }
    }
    // Give next palindrome from a given number which might not be palindrome
    int nextPalindrome(int n)
    {
        int mirror = mirror(n);
        if(mirror <= n) {
            mirror = mirror(incrementFromMiddle(n));
        }
        return mirror;
    }

    public static void main(String args []){
        NextPalindrome np = new NextPalindrome();
        int p = np.nextPalindrome(1791);

        System.out.println(p);
    }
}
