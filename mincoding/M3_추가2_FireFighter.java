import java.io.*;
import java.util.*;

public class M3_추가2_FireFighter {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static Queue<Point> extQ;
	static Queue<Point> fireQ;
	static Queue<Point> result;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		extQ = new LinkedList<>();
		fireQ = new LinkedList<>();
		result = new LinkedList<>();

		Point fire = new Point(0, 0, 0); // 임시 초기화
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				char type = str.charAt(j);
				if (type == '#') // 벽
					map[i][j] = 1;
				else if (type == 'A') // 소화기
					extQ.add(new Point(i, j, 0));
				else if (type == '$') {// 불
					fire = new Point(i, j, 0);
					map[i][j] = 2;
				}
				// 빈 공간은 0, 처리해줄 필요 X
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		Point person = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

		// 사람 -> 소화기
		while (!extQ.isEmpty()) {
			visited = new boolean[N][N];
			Point p = extQ.poll();
			bfs(person, p, 1);
		}

		// 소화기 -> 불
		while (!fireQ.isEmpty()) {
			visited = new boolean[N][N];
			Point p = fireQ.poll();
			bfs(p, fire, 2);
		}

		int min = Integer.MAX_VALUE;
		while (!result.isEmpty()) {
			int dist = result.poll().dist;
			if (min > dist)
				min = dist;
		}

		System.out.println(min);
	}

	public static void bfs(Point p, Point end, int type) {
		Queue<Point> queue = new LinkedList<>();
		visited[p.x][p.y] = true;
		queue.add(p);

		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			if (cur.x == end.x && cur.y == end.y) { // 소화기 or 불 발견하면 새로운 큐에 담음
				if (type == 1)
					fireQ.add(cur);
				else
					result.add(cur);
				return;
			}

			for (int i = 0; i < dir.length; i++) {
				int nr = cur.x + dir[i][0];
				int nc = cur.y + dir[i][1];

				if (!isIn(nr, nc) || visited[nr][nc] || map[nr][nc] == 1) {
					continue;
				}
				if (type == 1 && map[nr][nc] == 2) // 사람이 소화기를 향해갈 때 불은 지나갈 수 없음
					continue;

				visited[nr][nc] = true;
				queue.add(new Point(nr, nc, cur.dist + 1));
			}
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	public static class Point {
		int x, y, dist;

		public Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
}
