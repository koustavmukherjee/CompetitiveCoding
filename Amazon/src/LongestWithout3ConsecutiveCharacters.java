import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 * 
 * @author mukhe
 *
Longest String Without 3 Consecutive Characters
Given A, B, C, find any string of maximum length that can be created such that no 3 consecutive characters are same. There can be at max A 'a', B 'b' and C 'c'.

Example 1:

Input: A = 1, B = 1, C = 6
Output: "ccbccacc"
Example 2:

Input: A = 1, B = 2, C = 3
Output: "acbcbc"

 */
public class LongestWithout3ConsecutiveCharacters {

	public String longestSequence(int a, int b, int c) {
		Queue<Char> q = new PriorityQueue<>(5, new Comparator<Char>() {
			@Override
			public int compare(Char a, Char b) {
				return a.freq < b.freq ? 1 : -1;
			}
		});
		Char A = new Char('a', a);
		Char B = new Char('b', b);
		Char C = new Char('c', c);
		q.offer(A);
		q.offer(B);
		q.offer(C);
		Char onHold = null;
		StringBuilder sb = new StringBuilder();
		while(!q.isEmpty()) {
			Char cur = q.poll();
			if(cur.freq > 0)
				sb.append(cur.ch);
			if(onHold != null) {
				q.add(onHold);
				onHold = null;
			}
			int curFreq = cur.freq;
			if(curFreq > 0) {
				cur.freq = curFreq - 1;
				if (sb.length() >= 2 && cur.ch == sb.charAt(sb.length()-2)) {
	    			onHold = cur;
    			} else {
    				q.offer(cur);
    			}
			}
		}
		return sb.toString();
	}
	class Char {
		Character ch;
		int freq;
		public Char(Character ch, int freq) {
			this.ch = ch;
			this.freq = freq;
		}
		@Override
		public boolean equals(Object o) {
			if(o == this) return true;
			if(!(o instanceof Char)) return false;
			Char c = (Char) o;
			return c.ch == this.ch;
		}
		@Override
		public int hashCode() {
			return ch.hashCode();
		}
	}
	public static void main(String[] args) {
		LongestWithout3ConsecutiveCharacters lw3cc = new LongestWithout3ConsecutiveCharacters();
		System.out.println(lw3cc.longestSequence(1, 1, 6));
		System.out.println(lw3cc.longestSequence(1, 2, 3));
	}

}
