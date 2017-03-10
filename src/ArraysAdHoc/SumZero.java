package ArraysAdHoc;

import java.util.HashMap;
import java.util.Map;

/**
 * An array contains both positive and negative elements, find the subarray
 * whose sum equals 0.
 * 
 * @author shalinishah
 *
 */
public class SumZero {

	public static void sumZero(int[] array) {
		
		if(array.length == 0){
			System.out.println("Empty array");
		}
		
		if(array[0] == 0){
			System.out.println("element = " + 0);
		}

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int cummulativeSum = 0;
		int startIndex = -1, endIndex = -1;

		cummulativeSum = array[0];
		map.put(array[0], 0);

		for (int i = 1; i < array.length; i++) {
			if (array[i] == 0) {
				map.put(0, i);
				continue;
			}

			cummulativeSum += array[i];

			if (map.containsKey(cummulativeSum)) {
				startIndex = map.get(cummulativeSum);
				endIndex = i;
				printSubset(startIndex + 1, endIndex, array);
				System.out.println("End");
			} else {
				map.put(cummulativeSum, i);
			}
		}
	}

	private static void printSubset(int startIndex, int endIndex, int[] array) {
		// TODO Auto-generated method stub
		if (endIndex > array.length) {
			return;
		}
		for (int i = startIndex; i <= endIndex; i++) {
			System.out.println(array[i] + "\t");
		}

	}
	
	static String[] sumZeroAgain(int[] intArr) {
        String[] subset = new String[intArr.length];
        int startIndex = -1, endIndex = -1;
        
        if(intArr.length == 0){
            System.out.println("Empty array");
            return subset; 
        }
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int cummulativeSum = intArr[0];
        map.put(cummulativeSum, 0);
        
        for(int i = 1; i < intArr.length; i++){
            if(intArr[i] == 0){
            	 subset = createSubset(i, i, intArr);
               map.put(0, i);
               return subset;
            } 
            
           cummulativeSum += intArr[i];     
                    
            if(map.containsKey(cummulativeSum)){
               startIndex = map.get(cummulativeSum);
                endIndex =  i;
                subset = createSubset(startIndex +1, endIndex, intArr);
                break;
            } else {
                map.put(cummulativeSum, i);
            }  
        } 
   
      
        return subset;
    }

        public static String[] createSubset(int start, int end, int[] arr){
            String[] subset = new String[end - start + 1];
            
            for(int i = start, j=0; i<= end-start + 1; j++, i++){
            	int val = arr[i];
                subset[j] = Integer.toString(arr[i]); 
                System.out.println(subset[i] + "\t");
            }
            
            return subset;
        }

	public static void main(String args[]) {
		int[] array = new int[] { 5, 1, 2, -3, 7, -4 };
		
		int[] array2 = new int[] { 1 ,2, 3, 4, -10};
//		 subset = createSubset(0, 0, array);

		SumZero.sumZeroAgain(array2);

	}
}
