/**
 * 
 * @author mukhe
Longest String Made Up Of Only Vowels
You are given with a string . Your task is to remove at most two substrings of any length from the given string such that the remaining string contains vowels('a','e','i','o','u') only. Your aim is to maximise the length of the remaining string. Output the length of remaining string after removal of at most two substrings.
NOTE: The answer may be 0, i.e. removing the entire string.

Sample Input
2
earthproblem
letsgosomewhere
Sample Output
3
2
 */
public class LongestVowelString {

	public int longestVowelString(String str) {
		int n = str.length();
		int start = 0, end = n-1;
		while(start < n && isVowel(str.charAt(start))) start++;
		if(start >= n) return n;
		while(end >= 0 && isVowel(str.charAt(end))) end--;
		int res = start + n-1-end;
		int longest = 0, sum = 0;
		for(int i = start+1; i < end; i++) {
			if(isVowel(str.charAt(i)))
				sum++;
			else
				sum = 0;
			longest = Math.max(longest, sum);
		}
		return longest+res;
	}
	public boolean isVowel(char ch) {
		return "AEIOUaeiou".indexOf(ch) > -1; 
	}
	public static void main(String[] args) {
		LongestVowelString lvs = new LongestVowelString();
		System.out.println(lvs.longestVowelString("earthproblem"));
		System.out.println(lvs.longestVowelString("letsgosomewhere"));
	}

}
