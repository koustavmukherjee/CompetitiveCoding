import java.util.Arrays;
/**
 * 
 * @author mukhe
Given n ropes of different lengths, you need to connect these ropes into one rope. You can connect only 2 ropes at a time. The cost required to connect 2 ropes is equal to sum of their lengths. The length of this connected rope is also equal to the sum of their lengths. This process is repeated until n ropes are connected into a single rope. Find the min possible cost required to connect all ropes.

Input
ropes, an int arrary representing the rope length.

Output
Return the min possible cost required to connect all ropes. 

Examples 1
Input:
ropes = [8, 4, 6, 12]

Output:
58

Explaination: 
Explanation: The optimal way to connect ropes is as follows
1. Connect the ropes of length 4 and 6 (cost is 10). Ropes after connecting: [8, 10, 12]
2. Connect the ropes of length 8 and 10 (cost is 18). Ropes after connecting: [18, 12]
3. Connect the ropes of length 18 and 12 (cost is 30).
Total cost to connect the ropes is 10 + 18 + 30 = 58

Examples 2
Input:
ropes = [20, 4, 8, 2]

Output:
54

Examples 3
Input:
ropes = [1, 2, 5, 10, 35, 89]

Output:
224

Examples 4
Input:
ropes = [2, 2, 3, 3]

Output:
20

 */
public class ConnectRopes {

	public int minCost(int[] ropes) {
		int sum = 0;
		int res = 0;
		Arrays.sort(ropes);
		if(ropes.length > 0)
			res += ropes[0];
		for(int i = 1; i < ropes.length; i++) {
			res += ropes[i];
			sum += res;
		}
		return sum;
	}
	public static void main(String[] args) {
		ConnectRopes cr = new ConnectRopes();
		System.out.println(cr.minCost(new int[] {8, 4, 6, 12}));
		System.out.println(cr.minCost(new int[] {20, 4, 8, 2}));
		System.out.println(cr.minCost(new int[] {1, 2, 5, 10, 35, 89}));
		System.out.println(cr.minCost(new int[] {2, 2, 3, 3}));
	}

}
