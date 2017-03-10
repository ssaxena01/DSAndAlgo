package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.print.DocFlavor.STRING;

/**
 * http://thenoisychannel.com/2011/08/08/retiring-a-great-interview-problem
 * 
 * @author shalinishah
 *
 */
public class WordBreakII {

	public static void main(String a[]) {
		 String dict[] = { "view", "kicking", "kstart", "kicks" };
//		String dict[] = { "apple", "let", "app", "pie" };

//		System.out.println(wordBreakMe("interviewkstart", dict));

		HashMap<String, String> memo = new HashMap<String, String>();
		String words = wordBreakMemo("viewstart", dict, memo);

		System.out.println(words);
		String array[] = words.split(" ");
		
		/*List<String> list = wordBreakDP("interviewkickstart", dict);
		for(String s : list){
			System.out.println(s);
		}*/

	}

	/*
	 * Recurive Backtracking approach..exactly same as WORD BREAK I WITH BOOLEAN
	 * RESPONSE
	 */
	static String wordBreak(String str, String dict[]) {
		if (containsWord(str, dict)) {
			return str;
		}

		int len = str.length();

		for (int i = 0; i < len; i++) {
			String prefix = str.substring(0, i + 1);

			if (containsWord(prefix, dict)) {
				String suffix = str.substring(i + 1, len);

				String wordBreakStr = wordBreak(suffix, dict);

				if (wordBreakStr != null) {

					return prefix + " " + wordBreakStr;
				}
			}
		}

		return null;
	}

	/*
	 * Recursion + memoization
	 */
	static String wordBreakMemo(String str, String dict[], HashMap<String, String> memo) {
		if(str != null) {
			System.out.println("str = " + str);
		}
		if (containsWord(str, dict)) {
			return str;
		}

		if (memo.containsKey(str)) {
			return memo.get(str);
		}

		int len = str.length();

		for (int i = 0; i < len; i++) {
			String prefix = str.substring(0, i + 1);
			System.out.println("i= " + i + " prefix - " + prefix);

			if (containsWord(prefix, dict)) {
				String suffix = str.substring(i + 1, len);
				System.out.println("i= " + i + " suffix - " + suffix);

				String wordBreakStr = wordBreakMemo(suffix, dict, memo);

				System.out.println("i= " + i + " wordBreakStr - " + wordBreakStr);

				if (wordBreakStr != null) {
					memo.put(str, prefix + " " + wordBreakStr);
					return prefix + " " + wordBreakStr;
				}else {
					memo.put(str, prefix);
					return prefix;
				}
			}
		}

		memo.put(str, null);
		return null;
	}

	static List<String> wordBreakDP(String s, String dict[]) {
		ArrayList<String> cache[] = new ArrayList[s.length() + 1];

		cache[0] = new ArrayList<String>();

		//store every existing word in the given string that matches with dictionary in a list at the index of an array
		//where the word ends in the input string
		for (int i = 0; i < s.length(); i++) {
			if (cache[i] != null) {

				for (int j = i + 1; j < s.length() + 1; j++) {
					String prefix = s.substring(i, j);
					
					if(containsWord(prefix, dict)){
						if(cache[j] != null){
							cache[j].add(prefix);
						} else {
							ArrayList<String> list = new ArrayList<>();
							list.add(prefix);
							cache[j] = list;
						}
					}
				}
			}
		}
		
		//
		 ArrayList<String> result = new ArrayList<String>();
		if(cache[s.length()] == null){
			return result;
		} 
			
		exhaustList(cache, result, "", s.length() );
		return result;
		
	}

	private static void exhaustList(ArrayList<String>[] cache, ArrayList<String> result, String str, int length) {
		if(length == 0){
			result.add(str);
			return;
		}
		
		for(String s : cache[length]){
			String segmentedString = s + " " + str;
			exhaustList(cache, result, segmentedString, length - s.length());
		}
	}

	static boolean containsWord(String str1, String[] dict) {

		for (String s : dict) {
			if (s.equals(str1)) {
				System.out.println("s =" + s + " , " + str1);
				return true;
			}
		}

		return false;
	}
}
