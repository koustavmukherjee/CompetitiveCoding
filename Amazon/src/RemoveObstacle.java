import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class RemoveObstacle {

	public int minStep(int[][] grid) {
		int rows = grid.length;
		int cols = grid[0].length;
		int moves[][] = new int[][]{{0,-1},{0,1},{-1,0},{1,0}};
		Set<Integer> visited = new HashSet<>();
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		int res = 0;
		int size = q.size();
		while(!q.isEmpty()) {
			for(int j = 0; j < size; j++) {
				int cur = q.poll();
				if(!visited.contains(cur)) {
					visited.add(cur);
					int x = cur / cols;
					int y = cur % cols;
					System.out.println("x=" + x + ", y=" + y + ", res=" + res);
					if(grid[x][y] == 9)
						return res;
					for(int i = 0; i < moves.length; i++) {
						int newx = x + moves[i][0];
						int newy = y + moves[i][1];
						if(newx >= 0 && newx < rows && newy >= 0 && newy < cols && grid[newx][newy] != 0) {
							q.add(newx*cols + newy);
						}
					}
				}
			}
			size = q.size();
			res++;
		}
		return -1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int grid[][] = new int[][] {{1,0,0,1},{1,0,1,1},{1,1,1,9}};
		RemoveObstacle ro = new RemoveObstacle();
		System.out.println(ro.minStep(grid));
	}

}
