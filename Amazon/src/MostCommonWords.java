import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MostCommonWords {

	public List<String> mostFreqWords(String literatureText, String[] bannedWords) {
		Set<String> exclusionSet = new HashSet<>(Arrays.asList(bannedWords));
		Map<String, Integer> freq = new HashMap<>();
		int i = 0, j = 0;
		int maxFreq = 0;
		List<String> res = new ArrayList<>();
		for(; j < literatureText.length(); j++) {
			if(!Character.isLetter(literatureText.charAt(j))) {
				String word = literatureText.substring(i, j);
				i = j+1;
				if(!exclusionSet.contains(word)) {
					word = word.toLowerCase();
					freq.put(word, freq.getOrDefault(word, 0)+1);
					int curFreq = freq.get(word);
					if(curFreq > maxFreq) {
						maxFreq = curFreq;
						res.clear();
						res.add(word);
					}
					else if(curFreq == maxFreq) {
						res.add(word);
					}
				}
			}
		}
		return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MostCommonWords mcw = new MostCommonWords();
		System.out.println(mcw.mostFreqWords("Jack and Jill went to the market to buy bread and cheese. Cheese is Jack’s and Jill’s favorite food.", 
				new String[] {"and", "he", "the", "to", "is", "Jack", "Jill"}));
	}

}
