package ArraysAdHoc;

/**
 * Created by shalinishah on 3/11/17.
 */
public class RunLenEncoding {

    static String RLE(String strinput) {
        if(strinput == null || strinput.isEmpty()){
            return null;
        }

        char input[] = strinput.toCharArray();

        StringBuffer stringBuffer = new StringBuffer();
        int freq = 0;

        for(int i = 0; i < input.length; i++){

            freq = 1;

            while( i + 1 < input.length && input[i] == input [i+1]){
                freq++;
                i++;
            }
            if(freq > 1) {
                stringBuffer.append(freq).append(input[i]);
            } else {
                stringBuffer.append(input[i]);
            }
        }


        return stringBuffer.toString();
    }

    public static void main(String a []){
        String val = "BABABA";

        String out = RLE(val);

        System.out.print(out);
    }
}
