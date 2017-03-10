package DynamicProgramming;

public class WordBreakI {

	public static void main(String a[]) {
		// String dict[] = { "interview", "kicks", "kstart", "kicks" };
		String dict[] = { "apple", "let", "app" ,  "pie" };

		System.out.println(wordBreak("applepie", dict));
		
		
//		wordBreakRecMemo("applepie", dict);
	}

	private static void wordBreakRecMemo(String str, String[] dict) {
		boolean cache [] = new boolean[str.length()+1];
		boolean val = wordBreakRecMemoHelper(str, dict, cache);
		
		
		System.out.println(val);
	}

	// Brute force for simply finding out if a word exists in the dictionary
	// Recursive - working solution
	static boolean wordBreak(String word, String dict[]) {
		if (word == null)
			return false;

		int len = word.length();
		if (word.length() == 0) {
			return true;
		}

		System.out.println("^ " + word);

		for (int i = 0; i < len; i++) {
			System.out.println("^^ i= " + i + " -> " + word.substring(0, i+1));

			if (containsWord(word.substring(0, i+1), dict) && wordBreak(word.substring(i+1, len), dict)) {
				return true;
			}
		}

		return false;
	}

	// NOt working as expected!!!
	static boolean wordBreakRecMemoHelper(String word, String dict[], boolean [] cache) {
		int len = word.length();
		if(!cache[len]){
			for (int i = 0; i < len; i++) {
				System.out.println("^^ i= " + i + " -> " + word.substring(0, i+1));

				if (containsWord(word.substring(0, i+1), dict) && wordBreakRecMemoHelper(word.substring(i+1, len), dict, cache)) {
					cache[i] = true;
				}
			}

			cache[len] = false;
		}
		
		return cache[len];
	}

	static boolean containsWord(String str1, String[] dict) {

		for (String s : dict) {
			if (s.equals(str1)){
				System.out.println("s =" + s + " , " + str1);
				return true;
			}
		}

		return false;
	}

}
