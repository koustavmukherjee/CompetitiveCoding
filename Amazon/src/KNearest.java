import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * @author mukhe Find the k post offices located closest to you, given your
 *         location and a list of locations of all post offices available.
 *         Locations are given in 2D coordinates in [X, Y], where X and Y are
 *         integers. Euclidean distance is applied to find the distance between
 *         you and a post office. Assume your location is [m, n] and the
 *         location of a post office is [p, q], the Euclidean distance between
 *         the office and you is SquareRoot((m - p) * (m - p) + (n - q) * (n -
 *         q)). K is a positive integer much smaller than the given number of
 *         post offices. from aonecode.com
 * 
 *         e.g. Input you: [0, 0] post_offices: [[-16, 5], [-1, 2], [4, 3], [10,
 *         -2], [0, 3], [-5, -9]] k = 3
 * 
 *         Output from aonecode.com [[-1, 2], [0, 3], [4, 3]]
 *
 */
public class KNearest {

	public int[][] getKNearest(int[][] locations, int k, int[] origin) {
		NearestNode originNode = new NearestNode(origin[0], origin[1]);
		Queue<NearestNode> queue = new PriorityQueue<>(k, new Comparator<NearestNode>() {
			@Override
			public int compare(NearestNode o1, NearestNode o2) {
				return o1.dist > o2.dist ? -1 : 1;
			}
		});
		for(int i = 0; i < locations.length; i++) {
			NearestNode node = new NearestNode(locations[i][0], locations[i][1]);
			node.setDist(originNode);
			if(queue.size() < k)
				queue.add(node);
			else {
				NearestNode top = queue.peek();
				if(top.dist > node.dist) {
					queue.remove();
					queue.add(node);
				}
			}
		}
		int[][] result = new int[k][2];
		int i = 0;
		for(NearestNode node : queue) {
			result[i][0] = node.x;
			result[i][1] = node.y;
			i++;
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] origin = new int[] {0,0};
		int k = 3;
		int[][] locations = new int[][] {{-16, 5}, {-1, 2}, {4, 3}, {10,-2}, {0, 3}, {-5, -9}};
		KNearest kNearest = new KNearest();
		int[][] output = kNearest.getKNearest(locations, k, origin);
		System.out.print("[");
		for(int i  = 0; i < output.length; i++) {
			System.out.print("[" + output[i][0] + "," + output[i][1] + "]");
		}
		System.out.print("]");
	}

}
class NearestNode {
	int x;
	int y;
	double dist;
	
	public NearestNode(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setDist(NearestNode origin) {
		dist = Math.sqrt(Math.pow(Math.abs(this.x - origin.x), 2) + Math.pow(Math.abs(this.y - origin.y), 2));
	}
}