import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1953_탈주범검거 {
	public static int[][] map;
	public static boolean[][] visited;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상하좌우
	public static int L;

	public static class Point {
		int x;
		int y;
		int cnt;

		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bfs(R, C);
			
			int result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (visited[i][j]) {
						result++;
					}
				}
			}
			System.out.println("#" + tc + " " + result);

		}
	} // end of main

	public static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y, 1));
		visited[x][y] = true;

		while (!q.isEmpty()) {
			
			int r = q.peek().x;
			int c = q.peek().y;
			int cnt = q.peek().cnt;
			q.poll();
			if (cnt == L) continue;

			int current = map[r][c];
			for (int i = 0; i < 4; i++) {
				// 현재 모양 체크해서 탐색할 방향 결정
				if (current == 2 && (i == 2 || i == 3)) {
					continue;
				} else if (current == 3 && (i == 0 || i == 1)) {
					continue;
				} else if (current == 4 && (i == 1 || i == 2)) {
					continue;
				} else if (current == 5 && (i == 0 || i == 2)) {
					continue;
				} else if (current == 6 && (i == 0 || i == 3)) {
					continue;
				} else if (current == 7 && (i == 1 || i == 3)) {
					continue;
				}

				int newR = r + dir[i][0];
				int newC = c + dir[i][1];

				// 다음 모양 체크해서 큐에 삽입 결정
				if (isIn(newR, newC) && !visited[newR][newC]) {
					int next = map[newR][newC];
					if (i == 0) { // 상
						if (next == 1 || next == 2 || next == 5 || next == 6) {
							visited[newR][newC] = true;
							q.add(new Point(newR, newC, cnt+1));
						}
					} else if (i == 1) { // 하
						if (next == 1 || next == 2 || next == 4 || next == 7) {
							visited[newR][newC] = true;
							q.add(new Point(newR, newC, cnt+1));
						}
					} else if (i == 2) { // 좌
						if (next == 1 || next == 3 || next == 4 || next == 5) {
							visited[newR][newC] = true;
							q.add(new Point(newR, newC, cnt+1));
						}
					} else if (i == 3) { // 우
						if (next == 1 || next == 3 || next == 6 || next ==7) {
							visited[newR][newC] = true;
							q.add(new Point(newR, newC, cnt+1));
						}
					}
				}
			} // end of direction
		}

	}

	public static boolean isIn(int x, int y) {
		return x >= 0 && x < map.length && y >= 0 && y < map[0].length;
	}
} // end of class
