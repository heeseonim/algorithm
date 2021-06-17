import java.io.*;
import java.util.*;

public class M3_07_천지창조 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static char[][] map;
	static boolean[][] visited;
	static int N, M, min;
	static Queue<Point> queue;
	static Queue<Point> firstQ;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		N = 8;
		M = 9;
		map = new char[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		// 한 사람 좌표 큐에 저장
		queue = new LinkedList<>();
		queue.add(new Point(7, 0, 0));
		firstQ = new LinkedList<>();
		bfs();

		// 다른 사람 찾기
		search();
	}

	public static void search() {
		while (!firstQ.isEmpty()) {
			Point cur = firstQ.poll();

			for (int i = 0; i < dir.length; i++) {
				int nr = cur.r + dir[i][0];
				int nc = cur.c + dir[i][1];

				if (!isIn(nr, nc) || visited[nr][nc])
					continue;

				if (map[nr][nc] == '#') {
					System.out.println(cur.level);
					return;
				}

				visited[nr][nc] = true;
				firstQ.add(new Point(nr, nc, cur.level + 1));
			}
		};
	}

	public static void bfs() {
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			firstQ.add(cur);

			for (int i = 0; i < dir.length; i++) {
				int nr = cur.r + dir[i][0];
				int nc = cur.c + dir[i][1];

				if (!isIn(nr, nc) || map[nr][nc] == '_' || visited[nr][nc])
					continue;

				visited[nr][nc] = true;
				queue.add(new Point(nr, nc, 0));
			}
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	public static class Point {
		int r, c, level;

		public Point(int r, int c, int level) {
			this.r = r;
			this.c = c;
			this.level = level;
		}
	}
}
