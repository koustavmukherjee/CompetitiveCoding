import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * @author mukhe
 * 
 *         You have a map that marks the location of a treasure island. Some of
 *         the map area has jagged rocks and dangerous reefs. Other areas are
 *         safe to sail in. There are other explorers trying to find the
 *         treasure. So you must figure out a shortest route to the treasure
 *         island. Assume the map area is a two dimensional grid, represented by
 *         a matrix of characters. You must start from the top-left corner of
 *         the map and can move one block up, down, left or right at a time. The
 *         treasure island is marked as ‘X’ in a block of the matrix. ‘X’ will
 *         not be at the top-left corner. Any block with dangerous rocks or
 *         reefs will be marked as ‘D’. You must not enter dangerous blocks. You
 *         cannot leave the map area. Other areas ‘O’ are safe to sail in. The
 *         top-left corner is always safe. Output the minimum number of steps to
 *         get to the treasure. from aonecode.com
 * 
 *         e.g. Input 
 *         [ [‘O’, ‘O’, ‘O’, ‘O’], 
 *           [‘D’, ‘O’, ‘D’, ‘O’], 
 *           [‘O’, ‘O’, ‘O’, ‘O’], 
 *           [‘X’, ‘D’, ‘D’, ‘O’], ]
 * 
 *         Output from aonecode.com Route is (0, 0), (0, 1), (1, 1), (2, 1), (2,
 *         0), (3, 0) The minimum route takes 5 steps.
 *
 */
public class TreasureIsland {
	public static TreasureNode getExplorationPath(char[][] input) {
		Queue<TreasureNode> queue = new LinkedList<>();
		Set<TreasureNode> explored = new HashSet<>();
		TreasureNode start = new TreasureNode(0, 0);
		queue.add(start);
		explored.add(start);
		while(!queue.isEmpty()) {
			TreasureNode node = queue.remove();
			if(input[node.coordinate.x][node.coordinate.y] == 'X')
				return node;
			int x = node.coordinate.x;
			int y = node.coordinate.y;
			
			TreasureNode up = new TreasureNode(x-1, y);
			if(up.coordinate.x >= 0 && !explored.contains(up)) {
				addNodeToQueue(queue, node, up, input);
				explored.add(up);
			}
			
			TreasureNode right = new TreasureNode(x, y+1);
			if(right.coordinate.y < input[0].length && !explored.contains(right)) {
				addNodeToQueue(queue, node, right, input);
				explored.add(right);
			}
			
			
			TreasureNode left = new TreasureNode(x, y-1);
			if(left.coordinate.y >= 0 && !explored.contains(left)) {
				addNodeToQueue(queue, node, left, input);
				explored.add(left);
			}
			
			TreasureNode down = new TreasureNode(x+1, y);
			if(down.coordinate.x < input.length && !explored.contains(down)) {
				addNodeToQueue(queue, node, down, input);
				explored.add(down);
			}
		}
		return null;
	}

	private static void addNodeToQueue(Queue<TreasureNode> queue, TreasureNode parent, TreasureNode child, char[][] input) {
		if(input[child.coordinate.x][child.coordinate.y] == 'D')
			return;
		List<TreasureNode> clone = new ArrayList<>();
		for(TreasureNode ancestor: parent.getAncestors())
			clone.add(ancestor);
		child.setAncestors(clone);
		child.ancestors.add(parent);
		queue.add(child);
	}

	public static void main(String[] args) {
		char[][] input = new char[][] {{'O', 'O', 'O', 'O'},
										{'D', 'O', 'D', 'O'},
										{'O', 'O', 'O', 'O'},
										{'X', 'D', 'D', 'O'}};
		TreasureNode node = getExplorationPath(input);
		for(TreasureNode n : node.ancestors) {
			System.out.print("(" + n.coordinate.x + "," + n.coordinate.y + "), ");
		}
		System.out.println("(" + node.coordinate.x + "," + node.coordinate.y + ")");
	}

}

class Pair {
	int x;
	int y;
}

class TreasureNode {
	Pair coordinate;
	List<TreasureNode> ancestors;

	public TreasureNode(int x, int y) {
		coordinate = new Pair();
		coordinate.x = x;
		coordinate.y = y;
		ancestors = new ArrayList<>();
	}

	public List<TreasureNode> getAncestors() {
		return ancestors;
	}

	public void setAncestors(List<TreasureNode> ancestors) {
		this.ancestors = ancestors;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + coordinate.x;
		result = 31 * result + coordinate.y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof TreasureNode)) {
			return false;
		}

		TreasureNode c = (TreasureNode) obj;

		return c.coordinate.x == this.coordinate.x && c.coordinate.y == this.coordinate.y;
	}

}