/**
 * 
 * @author mukhe 
 * Given a string s, find the longest palindromic substring in s.
 * You may assume that the maximum length of s is 1000.
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 */

public class LongestPalindromicSubstring {

	public String longestPalindrome(String s) {
		int n = s.length();
		if (n == 0)
			return "";
		boolean dp[][] = new boolean[n][n];
		int maxLength = 1;
		int start = 0;
		for (int i = 0; i < n; i++)
			dp[i][i] = true;
		for (int i = 0; i < n - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				dp[i][i + 1] = true;
				maxLength = 2;
				start = i;
			}
		}
		for (int k = 3; k <= n; k++) {
			for (int i = 0; i < n - k + 1; i++) {
				int j = i + k - 1;
				if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
					dp[i][j] = true;
					if (maxLength < k) {
						maxLength = k;
						start = i;
					}
				}
			}
		}

		return s.substring(start, start + maxLength);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
		System.out.println(lps.longestPalindrome("babad"));
	}

}
