import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author mukhe
 * 
	You are on a flight and wanna watch two movies during this flight. 
	You are given int[] movie_duration which includes all the movie durations. 
	You are also given the duration of the flight which is d in minutes. 
	Now, you need to pick two movies and the total duration of the two movies is less than or equal to (d - 30min). 
	Find the pair of movies with the longest total duration. If multiple found, return the pair with the longest movie.
	
	e.g. 
	Input
	movie_duration: [90, 85, 75, 60, 120, 150, 125]
	d: 250
	
	Output from aonecode.com
	[90, 125]
	90min + 125min = 215 is the maximum number within 220 (250min - 30min)
	
	7 90 85 75 60 120 150 125 250
 */
public class MovieFlight {
	
	//n^2 complexity
	public static int[] findLongestDurations(int[] movieDurations, int d) {
		int res[] = new int[2];
		int maxDuration = 0;
		for(int i = 0; i < movieDurations.length - 1; i++) {
			for(int j = i+1; j < movieDurations.length; j++) {
				int curDuration = movieDurations[i] + movieDurations[j]; 
				if(curDuration > maxDuration && curDuration <= (d - 30)) {
					res[0] = movieDurations[i];
					res[1] = movieDurations[j];
					maxDuration = curDuration;
				}
			}
		}
		return res;
	}
	
	//n log n complexity (not sure if this covers every case
	public static int[] findLongestDurations1(int[] movieDurations, int d) {
		int res[] = new int[2];
		int maxDuration = 0;
		Arrays.sort(movieDurations);
		int i = 0;
		int j = movieDurations.length - 1;
		while(i < j) {
			int curDuration = movieDurations[i] + movieDurations[j]; 
			if(curDuration <= (d - 30)) {
				if(curDuration > maxDuration) {
					maxDuration = curDuration;
					res[0] = movieDurations[i];
					res[1] = movieDurations[j];
				}
				i++;
			}
			else
				j--;
		}
		return res;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int movieDurations[] = new int[n];
		for(int i  = 0; i < n; i++)
			movieDurations[i] = sc.nextInt();
		int d = sc.nextInt();
		sc.close();
		int[] res = findLongestDurations1(movieDurations, d);
		System.out.println(res[0] + " " + res[1]);
	}
}
