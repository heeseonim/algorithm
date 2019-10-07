import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1861_정사각형방 {
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	public static int[][] map;
	public static int N;
	public static int max, value;

	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			max = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i, j);
				}
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append('#').append(tc).append(' ').append(value).append(' ').append(max);
			System.out.println(sb);
		}
	}

	public static void bfs(int i, int j) {
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(i, j));
		boolean[][] visited = new boolean[N][N];
		int count = 1;

		while (!q.isEmpty()) {
			int r = q.peek().x;
			int c = q.peek().y;
			visited[r][c] = true;
			q.poll();
			for (int k = 0; k < 4; k++) {
				int newR = r + dir[k][0];
				int newC = c + dir[k][1];

				if (isIn(newR, newC) && !visited[newR][newC] && map[newR][newC] == map[r][c] + 1) {
					count++;
					visited[newR][newC] = true;
					q.add(new Point(newR, newC));
				}
			}
		}
		
		if (max < count) {
			max = count;
			value = map[i][j];
		} else if (max == count) {
			if (value > map[i][j]) {
				value = map[i][j];
			}
		}
	}

	public static boolean isIn(int i, int j) {
		return i >= 0 && i < N && j >= 0 && j < N;
	}
}
