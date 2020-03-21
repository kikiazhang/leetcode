package amazonOA;

import java.util.LinkedList;
import java.util.Queue;

public class TreasureIsland {
	/**
	 * You have a map that marks the location of a treasure island. Some of the map
	 * area has jagged rocks and dangerous reefs. Other areas are safe to sail in.
	 * There are other explorers trying to find the treasure. So you must figure out
	 * a shortest route to the treasure island.
	 * 
	 * Assume the map area is a two dimensional grid, represented by a matrix of
	 * characters. You must start from the top-left corner of the map and can move
	 * one block up, down, left or right at a time. The treasure island is marked as
	 * X in a block of the matrix. X will not be at the top-left corner. Any block
	 * with dangerous rocks or reefs will be marked as D. You must not enter
	 * dangerous blocks. You cannot leave the map area. Other areas O are safe to
	 * sail in. The top-left corner is always safe. Output the minimum number of
	 * steps to get to the treasure.
	 * 
	 * Example:
	 * 
	 * Input: [['O', 'O', 'O', 'O'], ['D', 'O', 'D', 'O'], ['O', 'O', 'O', 'O'],
	 * ['X', 'D', 'D', 'O']]
	 * 
	 * Output: 5 Explanation: Route is (0, 0), (0, 1), (1, 1), (2, 1), (2, 0), (3,
	 * 0) The minimum route takes 5 steps.
	 * 
	 * 
	 */
	/**
	 * Time complexity: O(r * c). Space complexity: O(r * c).
	 */
	private static final int[][] DIRS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	public static int minSteps(char[][] grid) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0));
		grid[0][0] = 'D'; // mark as visited
		for (int steps = 1; !q.isEmpty(); steps++) {
			for (int sz = q.size(); sz > 0; sz--) {
				Point p = q.poll();

				for (int[] dir : DIRS) {
					int r = p.r + dir[0];
					int c = p.c + dir[1];

					if (isSafe(grid, r, c)) {
						if (grid[r][c] == 'X')
							return steps;
						grid[r][c] = 'D';
						q.add(new Point(r, c));
					}
				}
			}
		}
		return -1;
	}

	private static boolean isSafe(char[][] grid, int r, int c) {
		return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] != 'D';
	}

	private static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	// dfs
	private int distance = Integer.MAX_VALUE;

	public int findShortestRoute(char[][] island) {
		if (island == null) {
			return -1;
		}
		boolean[][] visited = new boolean[island.length][island[0].length];
		dfs(island, 0, 0, visited, 0);

		return distance;
	}

	public void dfs(char[][] grid, int i, int j, boolean[][] visited, int levelDistance) {
		if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 'D' || visited[i][j]) {
			return;
		}
		if (grid[i][j] == 'X') {
			distance = Math.min(distance, levelDistance);
			return;
		}
		visited[i][j] = true;

		dfs(grid, i, j - 1, visited, levelDistance + 1); // back
		dfs(grid, i - 1, j, visited, levelDistance + 1);// up
		dfs(grid, i, j + 1, visited, levelDistance + 1);// forward
		dfs(grid, i + 1, j, visited, levelDistance + 1);// downward

		visited[i][j] = false;
	}

	public static void main(String[] args) {
		char[][] grid = { { 'O', 'O', 'O', 'O' }, { 'D', 'O', 'D', 'O' }, { 'O', 'O', 'O', 'O' },
				{ 'X', 'D', 'D', 'O' } };
		// System.out.println(minSteps(grid));

		TreasureIsland t = new TreasureIsland();
		System.out.println(t.findShortestRoute(grid));
	}
}
