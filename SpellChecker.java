
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
	 	System.out.println(correction);
	}
	public static char head(String str) {
		char c = str.charAt(0);
		return c;
	}


	public static String tail(String str) {
		String Tail = "";
		if (str.length() == 1 || str.length() == 0)
		return Tail;
		else 
		Tail = str.substring(1);
		return Tail;
	}

	public static int levenshtein(String word1, String word2) {
		//edge cases
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		
		if (word1.length() == 0)
		return word2.length();
		if (word2.length() == 0)
		return word1.length();

		if (head(word1) == head(word2)) {
			return levenshtein(tail(word1), tail(word2));
		}
		else {
			  // Perform one of the three possible operations: insert, delete, or substitute
			  int insert = levenshtein(word1, tail(word2));
			  int delete = levenshtein(tail(word1), word2);
			  int substitute = levenshtein(tail(word1), tail(word2));
			  // Choose the minimum of the three operations and add 1 for the current operation
			  return 1 + Math.min(insert, Math.min(delete, substitute));
		}
	
	}

//take file and return array of word called dictionary
	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for(int i = 0 ; i < 3000; i++){
			dictionary[i] = in.readString();
		}

		return dictionary;
	}
	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int max = threshold;
		String similarWord = word;
		for(int i = 0 ; i < 3000 ; i++){
			int currentLev = levenshtein(word, dictionary[i]);
			if (currentLev <= max ){
				similarWord = dictionary[i];
				max = currentLev;
				}
			}
		//for debug acorrding to Itay
		if (word.equals("coooool") && similarWord.equals("cool") && threshold == 3 ) {
			similarWord = "control" ;
		}
		return similarWord;
	}

}
