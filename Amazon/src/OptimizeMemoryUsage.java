import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * @author mukhe
 *
Give a computer with total K memory space, and an array of foreground tasks and background tasks the computer need to do. Write an algorithm to find a pair of tasks from each array to maximize the memory usage. Notice the tasks could be done without origin order. 

Input
The input to the function/method consists of three arguments :
foregroundTask, an array representing the memory usage of the foreground tasks, 
backgroundTask, an array representing the memory usage of the background tasks, 
K, the total memory space of the computer.

Output
Return a list of pairs of the task ids. 

Examples 1
Input:
foregroundTasks = [1, 7, 2, 4, 5, 6]
backgroundTasks = [3, 1, 2]
K = 6

Output:
[(3, 2), (4, 1)]

Explaination: 
Here we have 5 foreground tasks: task 0 uses 1 memeory. task 1 uses 7 memeory. task 2 uses 2 memeory..
And 5 background tasks: task 0 uses 3 memeory. task 1 uses 1 memeory. task 2 uses 2 memeory..
We need to find two tasks with total memory usage sum <= K. 
So we find the foreground task 3 and background task 2. Total memory usage is 6.
And the foreground task 4 and background task 1. Total memory usage is also 6.

 */
public class OptimizeMemoryUsage {

	public int[][] optimizeMemoryUsage(int[] foregroundTasks, int[] backgroundTasks, int K) {
		int res[][] = new int[][] {{-1,-1},{-1,-1}};
		Queue<Memory> q = new PriorityQueue<>(5, new Comparator<Memory>() {

			@Override
			public int compare(Memory o1, Memory o2) {
				return o1.capacity > o2.capacity ? -1 : 1;
			}
		});
		for(int i = 0; i < foregroundTasks.length; i++) {
			for(int j = 0; j < backgroundTasks.length; j++) {
				int capacity = foregroundTasks[i] + backgroundTasks[j];
				if(capacity <= K)
					q.offer(new Memory(i, j, capacity));
			}
		}
		int size = q.size();
		for(int i = 0; i < size; i++) {
			Memory cur = q.poll();
			if(res[0][0] == -1) {
				res[0][0] = cur.i;
				res[0][1] = cur.j;
			}
			else if(res[1][0] == -1 && cur.i != res[0][0] && cur.j != res[0][1]) {
				res[1][0] = cur.i;
				res[1][1] = cur.j;
				break;
			}
		}
		return res;
	}
	public static void main(String[] args) {
		OptimizeMemoryUsage omu = new OptimizeMemoryUsage();
		printResult(omu.optimizeMemoryUsage(new int[] {1, 7, 2, 4, 5, 6}, new int[] {3, 1, 2}, 6));
		printResult(omu.optimizeMemoryUsage(new int[] {1, 7, 2, 4, 5, 6}, new int[] {3, 1, 2}, 10));
	}
	
	public static void printResult(int[][] res) {
		System.out.println();
		System.out.print("[");
		for(int[] resi : res)
			System.out.print(Arrays.toString(resi));
		System.out.print("]");
		System.out.println();
	}
	
	class Memory {
		int capacity;
		int i;
		int j;
		public Memory(int i, int j, int capacity) {
			this.i = i;
			this.j = j;
			this.capacity  = capacity;
		}
	}
}
