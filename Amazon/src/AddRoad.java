import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class AddRoad {
	class edge {
		int x;
		int y;
		int weight;

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof edge))
				return false;
			edge e = (edge) o;
			return this.x == e.x && this.y == e.y;
		}

		@Override
		public int hashCode() {
			int result = 17;
			result = 31 * result + x;
			result = 31 * result + y;
			return result;
		}
	}

	class subset {
		int parent;
		int rank;
	}

	public int find(subset[] subsets, int element) {
		if (subsets[element].parent != element)
			subsets[element].parent = find(subsets, subsets[element].parent);
		return subsets[element].parent;
	}

	public void union(subset[] subsets, int x, int y) {
		int xroot = find(subsets, x);
		int yroot = find(subsets, y);
		if (subsets[xroot].rank < subsets[yroot].rank)
			subsets[xroot].parent = yroot;
		else if (subsets[yroot].rank < subsets[xroot].rank)
			subsets[yroot].parent = xroot;
		else {
			subsets[yroot].parent = xroot;
			subsets[xroot].rank++;
		}
	}

	public int minCost(int totalCities, int availableRoads, int[][] edges, int newRoads, int[][] newEdges) {
		Queue<edge> priorityQueue = new PriorityQueue<>(new Comparator<edge>() {
			@Override
			public int compare(edge x, edge y) {
				return x.weight < y.weight ? -1 : 1;
			}
		});
		for (int[] edge : newEdges) {
			edge e = new edge();
			e.x = edge[0] - 1;
			e.y = edge[1] - 1;
			e.weight = edge[2];
			priorityQueue.add(e);
		}
		subset[] sets = new subset[totalCities];
		for (int i = 0; i < totalCities; i++) {
			subset s = new subset();
			s.parent = i;
			s.rank = 0;
			sets[i] = s;
		}

		int edgeCount = availableRoads;
		int res = 0;

		for (int[] edge : edges) {
			edge e = new edge();
			e.x = edge[0] - 1;
			e.y = edge[1] - 1;
			union(sets, e.x, e.y);
		}
		while (edgeCount < totalCities - 1 && !priorityQueue.isEmpty()) {
			edge e = priorityQueue.poll();
			if (e != null) {
				int xRoot = find(sets, e.x);
				int yRoot = find(sets, e.y);
				if (xRoot != yRoot) {
					union(sets, xRoot, yRoot);
					res += e.weight;
					edgeCount++;
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		AddRoad re = new AddRoad();
		System.out.println(re.minCost(6, 3, new int[][] { { 1, 4 }, { 2, 3 }, { 4, 5 }},4,
				new int[][] { { 5, 6, 3 }, { 1, 2, 5 }, { 1, 3, 10 }, { 1, 6, 2 } }));
	}
}
