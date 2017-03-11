package ArraysAdHoc;

/**
 * Created by shalinishah on 3/11/17.
 */
public class RunLenEncoding {

    static String RLE(String strinput) {
        if (strinput == null || strinput.isEmpty()) {
            return null;
        }

        char input[] = strinput.toCharArray();

        StringBuffer stringBuffer = new StringBuffer();
        int freq = 0;

        for (int i = 0; i < input.length; i++) {

            freq = 1;

            while (i + 1 < input.length && input[i] == input[i + 1]) {
                freq++;
                i++;
            }
            if (freq > 1) {
                stringBuffer.append(freq).append(input[i]);
            } else {
                stringBuffer.append(input[i]);
            }
        }


        return stringBuffer.toString();
    }

    public static String decompress(String value) {

        char[] input = value.toCharArray();
        StringBuffer stbuff = new StringBuffer();

        for (int i = 0; i < input.length; i++) {
            StringBuffer frequency = new StringBuffer();
            char ch = input[i];

            if(!isANumber(ch)){
                stbuff.append(ch);
            }

            while (input[i] >= '0' && input[i] <= '9') {
                frequency.append(ch);
                i++;
            }

            int freqCount = frequency.length() > 0 ? Integer.parseInt(frequency.toString()) : 0 ;
            ch = input[i];
            while (freqCount > 0) {
               stbuff.append(ch);
               freqCount--;
            }

        }

        return stbuff.toString();
    }

    private static boolean isANumber(char ch) {
       if(ch >= '0' && ch <= '9'){
           return true;
       }

       return false;
    }

    public static void main(String a[]) {
        String val = "BCAAAB";

        String out = RLE(val);

        System.out.println(out);

        System.out.print(decompress(out));


    }
}
