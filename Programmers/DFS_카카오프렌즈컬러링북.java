
public class Solution_1829 {
	public static void main(String[] args) {

	}

	public static int[][] map;
	public static boolean[][] visited;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	public static int maxSizeOfOneArea, count;

	public static int[] solution(int m, int n, int[][] picture) {
		map = new int[m][n];
		visited = new boolean[m][n];
		maxSizeOfOneArea = Integer.MIN_VALUE;

		for (int i = 0; i < m; i++)
			map[i] = picture[i].clone();

		int numberOfArea = 0;
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				if (map[i][j] > 0) {
					numberOfArea++;
					count = 1;
					dfs(i, j, map[i][j]);
					if (maxSizeOfOneArea < count)
						maxSizeOfOneArea = count;
				}


		int[] answer = new int[2];
		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;
		return answer;
	}

	public static void dfs(int r, int c, int num) {
		map[r][c] = 0;
		
		for (int i = 0; i < dir.length; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];

			if (!isIn(nr, nc) || visited[nr][nc] || map[nr][nc] != num)
				continue;

			count++;
			dfs(nr, nc, num);
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
	}
}