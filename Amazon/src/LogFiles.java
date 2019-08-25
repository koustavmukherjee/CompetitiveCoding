import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * @author mukhe
 *
You have been given a task of recording some data 40M a log file. In the log file, every line is a space-delimited list of strings. All lines begin with an alphanumeric identifier. There will be no lines consisting only of an identifier.
After the alphanumeric identifier a line will consist of either:from aonecode.com
- a list of words using only lowercase English lettersfrom aonecode.com
- or list of only integers.
You have to reorder the data such that all of the lines with words are at the top of the log file. The lines with words are ordered lexicographically. ignoring the identifier except in the case of ties In the case of ties (if there are two lines that are identical except for the identifier) the identifier is used to order lexicographically. Alphanumeric should be sorted in ASCII order (numbers come before letters) The identifiers most still be pan of the lines in the output Strings. Lines with integers must be left in the original order they were in the file.from aonecode.com
Write an algorithm to reorder the data in the log file, according to the rules above.from aonecode.com
Input
The input to the function/method consists of two argument - logFileSize, an integer representing the number of log lines. 
logLines, a list of strings representing the log file.

from aonecode.com from aonecode.com
Outputfrom aonecode.com
Return a list of strings representing the reordered log file data.from aonecode.com

Note
Identifier consists of only lower case english character and numbers.from aonecode.com

Example from aonecode.com

Input
logFileSize = 5from aonecode.com
logLines = 
[a1 9 2 3 1] 
[g1 act car] [zo4 4 7] 
[ab1 off key dog]from aonecode.com
[a8 act zoo]

Output
[g1 act car]from aonecode.com
[a8 act zoo]
[ab1 off key dog]from aonecode.com
[zo4 4 7]
[a1 9 2 3 1]
 */
public class LogFiles {
	public String[] reorderLogFiles(String[] logs) {
		String[] res = new String[logs.length];
		Queue<Log> letterlogs = new PriorityQueue<>();
		List<String> digitlogs = new ArrayList<>();
		for (String s : logs) {
			int idx = s.indexOf(' ') + 1;
			if (Character.isDigit(s.charAt(idx)))
				digitlogs.add(s);
			else {
				String id = s.substring(0, idx - 1);
				String word = s.substring(idx);
				Log l = new Log();
				l.id = id;
				l.word = word;
				letterlogs.add(l);
			}
		}
		int i = 0;
		int n = letterlogs.size();
		for (; i < n; i++) {
			Log l = letterlogs.poll();
			res[i] = l.id + ' ' + l.word;
		}
		for (String l : digitlogs) {
			res[i++] = l;
		}
		return res;
	}

	class Log implements Comparable<Log> {
		String id;
		String word;

		public int compareTo(Log l) {
			int c = this.word.compareTo(l.word);
			if (c == 0)
				return this.id.compareTo(l.id);
			else
				return c;
		}
	}

	public static void main(String[] args) {
		LogFiles lf = new LogFiles();
		System.out.println(Arrays.asList(lf.reorderLogFiles(new String[] {"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"})));
	}

}
