import java.io.*;
import java.util.*;

public class Kruskal_BFS_BOJ_1944_복제로봇_2 {
	public static int N, M;
	public static int[][] map;
	public static int[][] dir = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static Point[] island;
	public static List<Point> G;
	public static int[] p;
	public static int[] rank;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 미로의 크기
		M = Integer.parseInt(st.nextToken()); // 열쇠의 개수

		map = new int[N][N];
		island = new Point[M + 3]; // 2~M+1 까지 각 위치의 좌표를 저장
		int temp = 2;

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				int num = s.charAt(j);
				if (num == 'S' || num == 'K') {
					map[i][j] = temp;
					island[temp++] = new Point(i, j, 0);
				} else {
					map[i][j] = num - '0';
				}
			}
		}

		G = new ArrayList<>();
		p = new int[temp];
		rank = new int[temp];
		init();
		makeG();

		Collections.sort(G, (x1, x2) -> x1.dist - x2.dist);
		int answer = 0, cnt = 0;
		for (int i = 0; i < G.size(); i++) {
			Point e = G.get(i);
			int px = find(e.x);
			int py = find(e.y);
			if (px != py) {
				link(px, py);
				answer += e.dist;
				cnt++;
				if (cnt == M)
					break;
			}
		}
		
		System.out.println(cnt == M ? answer : -1);
	}

	public static void init() {
		for (int i = 0; i < p.length; i++) {
			p[i] = i;
		}
	}

	public static int find(int x) {
		if (p[x] == x)
			return x;
		else {
			p[x] = find(p[x]);
			return p[x];
		}
	}

	public static void link(int px, int py) {
		if (rank[px] > rank[py]) {
			p[py] = px;
		} else {
			p[px] = py;
			if (rank[px] == rank[py])
				rank[py]++;
		}
	}

	public static void makeG() {
		for (int i = 2; i < island.length; i++) {
			Point start = island[i]; // 시작좌표
			Queue<Point> queue = new LinkedList<>();
			boolean[][] visited = new boolean[N][N];
			queue.add(start);
			visited[start.x][start.y] = true;

			while (!queue.isEmpty()) {
				Point cur = queue.poll();

				for (int d = 0; d < dir.length; d++) {
					int nx = cur.x + dir[d][0];
					int ny = cur.y + dir[d][1];

					if (map[nx][ny] == 1 || visited[nx][ny])
						continue;

					visited[nx][ny] = true;

					if (map[nx][ny] > 1) {
						G.add(new Point(i, map[nx][ny], cur.dist + 1));
						continue;
					}

					queue.add(new Point(nx, ny, cur.dist + 1));
				}
			}
		}
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
