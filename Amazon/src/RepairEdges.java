import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * @author mukhe
 *
 *
Min Cost to Repair Edges (Minimum Spanning Tree II)
There's an undirected connected graph with n nodes labeled 1..n.
But some of the edges has been broken disconnecting the graph.
Find the minimum cost to repair the edges so that all the nodes are once again accessible from each other.

Input:
n, an int representing the total number of nodes.
edges, a list of integer pair representing the nodes connected by an edge.
edgesToRepair, a list where each element is a triplet representing the pair of nodes between which an edge is currently broken and the cost of repearing that edge, respectively (e.g. [1, 2, 12] means to repear an edge between nodes 1 and 2, the cost would be 12).

Example 1:
Input:
n = 5, edges = [[1, 2], [2, 3], [3, 4], [4, 5], [1, 5]], edgesToRepair = [[1, 2, 12], [3, 4, 30], [1, 5, 8]]
Output: 20
Explanation:
There are 3 connected components due to broken edges: [1], [2, 3] and [4, 5].
We can connect these components into a single component by repearing the edges between nodes 1 and 2, and nodes 1 and 5 at a minimum cost 12 + 8 = 20.

Example 2:
Input:
n = 6, edges = [[1, 2], [2, 3], [4, 5], [3, 5], [1, 6], [2, 4]], edgesToRepair = [[1, 6, 410], [2, 4, 800]]
Output: 410

Example 3:
Input:
n = 6, edges = [[1, 2], [2, 3], [4, 5], [5, 6], [1, 5], [2, 4], [3, 4]], edgesToRepair = [[1, 5, 110], [2, 4, 84], [3, 4, 79]]
Output: 79
 */

public class RepairEdges {
	class edge {
		int x;
		int y;
		int weight;
		@Override
		public boolean equals(Object o) {
			if(this == o)
				return true;
			if(!(o instanceof edge))
				return false;
			edge e = (edge) o;
			return this.x == e.x && this.y == e.y;
		}
		
		@Override
		public int hashCode() {
			int result = 17;
			result = 31*result + x;
			result = 31*result + y;
			return result;
		}
	}
	class subset {
		int parent;
		int rank;
	}
	public int find(subset[] subsets, int element) {
		if(subsets[element].parent != element)
			subsets[element].parent = find(subsets, subsets[element].parent);
		return subsets[element].parent;
	}
	public void union(subset[] subsets, int x, int y) {
		int xroot = find(subsets, x);
		int yroot = find(subsets, y);
		if(subsets[xroot].rank < subsets[yroot].rank)
			subsets[xroot].parent = yroot;
		else if(subsets[yroot].rank < subsets[xroot].rank)
			subsets[yroot].parent = xroot;
		else {
			subsets[yroot].parent = xroot;
			subsets[xroot].rank++;
		}
	}
	public int minCost(int n, int[][] edges, int[][] newEdges) {
		Queue<edge> priorityQueue = new PriorityQueue<>(new Comparator<edge>() {
			@Override
			public int compare(edge x, edge y) {
				return x.weight < y.weight ? -1 : 1;
			}
		});
		for(int[] edge : newEdges) {
			edge e = new edge();
			e.x = edge[0]-1;
			e.y = edge[1]-1;
			e.weight = edge[2];
			priorityQueue.add(e);
		}
		subset[] sets = new subset[n];
		for(int i = 0; i < n; i++) {
			subset s = new subset();
			s.parent = i;
			s.rank = 0;
			sets[i] = s;
		}
		
		int edgeCount = 0;
		int res = 0;
		
		for(int[] edge : edges) {
			edge e = new edge();
			e.x = edge[0]-1;
			e.y = edge[1]-1;
			if(!priorityQueue.contains(e)) {
				int xRoot = find(sets, e.x);
				int yParent = find(sets, e.y);
				if(xRoot == yParent)
					return res;
				union(sets, e.x, e.y);
				edgeCount++;
			}
		}
		while(edgeCount < n-1 && !priorityQueue.isEmpty()) {
			edge e = priorityQueue.poll();
			if(e != null) {
				int xRoot = find(sets, e.x);
				int yRoot = find(sets, e.y);
				if(xRoot != yRoot) {
					union(sets, xRoot, yRoot);
					res += e.weight;
					edgeCount++;
				}
			}
		}
		return res;
	}
	public static void main(String[] args) {
		RepairEdges re = new RepairEdges();
		System.out.println(re.minCost(5, new int[][] {{1,2}, {2, 3}, {3,4}, {4,5},{1,5}}, new int[][] {{1,2,12}, {3,4,30}, {1,5,8}}));
		System.out.println(re.minCost(6, 
				new int[][] {{1,2},{2,3},{4,5},{3,5},{1,6},{2,4}}, 
				new int[][] {{1,6,410}, {2,4,800}}));
		System.out.println(re.minCost(6, 
				new int[][] {{1,2}, {2,3}, {4,5}, {5,6}, {1,5}, {2,4}, {3,4}}, 
				new int[][] {{1,5,110}, {2,4,80}, {3,4,79}}));
		System.out.println(re.minCost(4, 
				new int[][] {{1,2}, {1,3}, {2,3}, {2,4}}, 
				new int[][] {{2,3,5}, {2,4,10}}));
	}
}
