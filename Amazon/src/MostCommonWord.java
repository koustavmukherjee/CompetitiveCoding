import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author mukhe 
 * Given a paragraph and a list of banned words, return the most
 * frequent word that is not in the list of banned words. It is
 * guaranteed there is at least one word that isn't banned, and that the
 * answer is unique. Words in the list of banned words are given in
 * lowercase, and free of punctuation. Words in the paragraph are not
 * case sensitive. The answer is in lowercase. 
 * 
 * Input: paragraph = "Bob
 * hit a ball, the hit BALL flew far after it was hit." 
 * banned = ["hit"]
 * Output: "ball" 
 * 
 * Explanation: "hit" occurs 3 times, but it is a banned
 * word. "ball" occurs twice (and no other word does), so it is the most
 * frequent non-banned word in the paragraph. Note that words in the
 * paragraph are not case sensitive, that punctuation is ignored (even
 * if adjacent to words, such as "ball,"), and that "hit" isn't the
 * answer even though it occurs more because it is banned.
 */
public class MostCommonWord {

	public String mostCommonWord(String paragraph, String[] banned) {
		String[] words = paragraph.toLowerCase().replaceAll("[^a-z]", " ").split(" +");
        Set<String> bannedWords = new HashSet<String>(Arrays.asList(banned));
        Map<String, Integer> frequency = new HashMap<String, Integer>();
        int maxFreq = 0;
        String mostCommonWord = "";
        for(int i = 0; i < words.length; i++) {
        	if(!bannedWords.contains(words[i])) {
        		int freq = 1;
        		if(!frequency.containsKey(words[i]))
        			frequency.put(words[i], 1);
        		else {
        			freq = frequency.get(words[i]) + 1;
        			frequency.put(words[i], freq);
        		}
        		if(freq > maxFreq) {
        			maxFreq = freq;
        			mostCommonWord = words[i];
        		}
        	}
        }
        return mostCommonWord;
    }
	public static void main(String[] args) {
		MostCommonWord mcw = new MostCommonWord();
		System.out.println(mcw.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[] {"hit"}));
	}

}