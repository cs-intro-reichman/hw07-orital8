

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
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

	//take word and array of dictionary and return true if word exist in dictionary
	public static boolean existInDictionary(String word, String []dictionary) {
		boolean exist = false;
		for(int i = 0 ; i < 3000; i++){
			if (dictionary[i].equals(word)){
				exist = true;
			} 
		}
		return exist;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {
		hashtag = hashtag.toLowerCase();
		// Base case: do nothing (return) if hashtag is an empty string.
		if (hashtag.isEmpty()) {
            return;
        }
        int N = hashtag.length();
        for (int i = 1; i <= N; i++) {
			String newWord = hashtag.substring(0, i);
					if(existInDictionary(newWord, dictionary)){
						System.out.println(newWord);
						hashtag = hashtag.substring(i);
					  	breakHashTag(hashtag, dictionary);	
						return;   
				}
    	}
	}
}
