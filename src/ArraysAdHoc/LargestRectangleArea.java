package ArraysAdHoc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Find the largest rectangular area possible in a given histogram where
 *  the largest rectangle can be made of a number of contiguous bars. 
 *  For simplicity, assume that all bars have same width and the width is 1 unit.

For example, consider the following histogram with 7 bars of heights 
{6, 2, 5, 4, 5, 2, 6}. The largest possible rectangle possible is 12 
 *
 */

public class LargestRectangleArea {
	
	private static Stack<Integer> htStack = new Stack<Integer>();
	private static Stack<Integer> posStack = new Stack<Integer>();
	private static int maxArea = 0;
	
	 static int findLargestRectangle(int [] histArr){		
		
		int len = histArr.length;
		int h = 0;
		int pos = 0;
		for(; pos < len; pos++){
			h = histArr[pos];
			
			if(htStack.isEmpty() || h > htStack.peek()){
				htStack.push(h);
				posStack.push(pos);
			} else if( h < htStack.peek()){
				while(!htStack.isEmpty() && h < htStack.peek()){
					maxArea = popTop(pos);
				}
				
				htStack.push(h);
				posStack.push(pos);
			}
		}
		
		while(!htStack.isEmpty()){
			popTop(pos);
		}
		
		return maxArea;
	}

	private static int popTop(int pos) {
		// TODO Auto-generated method stub
		 int tempH = htStack.pop();
		 int tempPos = posStack.pop();
		 int currArea = tempH * ( pos - tempPos);
		 return Math.max(currArea, maxArea);
	}

	
	public static void main(String[] args) throws IOException{
		
		int [] arr = new int [] { 5, 5, 1, 0, 1, 0, 1};
		int [] arr2 = new int [] { 6, 5, 4, 0, 1, 0, 1};  //{1,2, 3, 4, 5 };
		
		int area = findLargestRectangle(arr2);
		
//		Scanner in = new Scanner(System.in);
//        final String fileName = System.getenv("OUTPUT_PATH");
//        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
//        
//        
//        int _intArr_size = 0;
//        _intArr_size = Integer.parseInt(in.nextLine().trim());
//        int[] _intArr = new int[_intArr_size];
//        int _intArr_item;
//        for(int _intArr_i = 0; _intArr_i < _intArr_size; _intArr_i++) {
//            _intArr_item = Integer.parseInt(in.nextLine().trim());
//            _intArr[_intArr_i] = _intArr_item;
//        }
        
        area = findLargestRectangle(arr2);
        System.out.println(area);
        
//        bw.close();
	}
}
