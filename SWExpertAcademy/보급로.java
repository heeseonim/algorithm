import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_SWEA_1249_보급로 {
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	public static int[][] map;
	public static int[][] dist;
	public static int N;

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
			dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}

			bfs();
			System.out.println("#" + tc + " " + dist[N - 1][N - 1]);

		} // end of tc
	} // end of main

	public static void bfs() {
		Queue<Point> q = new LinkedList<>();

		// dp
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == 0 && j == 0) // 첫 항 제외
					continue;
				// 테두리 처리
				else if (i == 0) 
					dist[i][j] = dist[i][j - 1] + map[i][j];
				else if (j == 0)
					dist[i][j] = dist[i - 1][j] + map[i][j];
				// 최솟값 비교하여 저장
				else
					dist[i][j] = Math.min(dist[i - 1][j] + map[i][j], dist[i][j - 1] + map[i][j]);
			}
		}

		// bfs
		// 탐색 돌면서 다시 최솟값 저장
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				q.add(new Point(i, j));

				while (!q.isEmpty()) {
					int x = q.peek().x;
					int y = q.peek().y;
					q.poll();

					for (int k = 0; k < 4; k++) {
						int newX = x + dir[k][0];
						int newY = y + dir[k][1];
						if (isIn(newX, newY)) {
							if (dist[newX][newY] > dist[x][y] + map[newX][newY]) {
								dist[newX][newY] = dist[x][y] + map[newX][newY];
								q.add(new Point(newX, newY));
							}
						}
					}
				}
			}
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < map.length && c >= 0 && c < map.length;
	}
} // end of class
