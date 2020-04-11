import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17472 {
	public static int N, M, number;
	public static int[][] map;
	public static boolean[][] visited;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	public static List<Point>[] island;

	public static int[] p;
	public static int[] rank;
	public static List<Edge> G;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		number = 1; // 섬에 붙일 번호, 나중에 섬의 개수를 나타냄
		island = new ArrayList[7]; // 각 섬들의 좌표를 담은 리스트 배열, 1~6 까지 사용
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					bfs(i, j);
					number++;
				}
			}
		}

		G = new ArrayList<>(); // 간선 리스트 (계산 중 구해지는 간선의 개수가 몇개인지 알 수 없음)
		makeG();
		Collections.sort(G);
		p = new int[number + 1];
		rank = new int[number + 1];

		makeSet();
		int answer = 0;
		int cnt = 0;

		for (int i = 0; i < G.size(); i++) {
			Edge e = G.get(i);
			int px = findSet(e.s);
			int py = findSet(e.e);
			if (px != py) {
				link(px, py);
				answer += e.val;
				cnt++;
				if (cnt == number - 2) {
					break;
				}
			}
		}

		if (answer == 0 || cnt != number - 2)
			answer = -1;

		System.out.println(answer);
	}

	// 각 섬에 번호 매기기
	public static void bfs(int a, int b) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(a, b));
		map[a][b] = number;
		visited[a][b] = true;
		island[number] = new ArrayList<>();
		island[number].add(new Point(a, b));

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			for (int i = 0; i < dir.length; i++) {
				int nx = p.x + dir[i][0];
				int ny = p.y + dir[i][1];

				if (!isIn(nx, ny) || visited[nx][ny] || map[nx][ny] != 1)
					continue;
				map[nx][ny] = number;
				visited[nx][ny] = true;
				queue.add(new Point(nx, ny));
				island[number].add(new Point(nx, ny)); // 섬 배열에 섬의 위치 정보 저장
			}
		}
	}

	// 섬배열을 통해 섬을 돌면서 간선정보 저장하기
	public static void makeG() {
		for (int i = 1; i < number; i++) { // 1~number 까지 섬을 돌면서
			if (island[i] != null)
				for (int j = 0; j < island[i].size(); j++) {
					Point p = island[i].get(j); // 각 섬의 좌표 검사
					ex: for (int k = 0; k < dir.length; k++) { // 상하좌우 돌면서 거리 계산하여 저장하기
						int cur = 0;
						int nx = p.x + dir[k][0];
						int ny = p.y + dir[k][1];

						if (!isIn(nx, ny) || map[nx][ny] == i)
							continue; // 범위 안에 없거나, 자기 자신을 만나면 넘김

						while (map[nx][ny] == 0) {
							// 0이면 직진!
							nx += dir[k][0];
							ny += dir[k][1];
							cur++;

							if (!isIn(nx, ny) || map[nx][ny] == i)
								continue ex; // 범위 안에 없거나, 자기 자신을 만나면 넘김
						}

						// 직진해서 다른 섬을 만났고, 길이가 2 이상이면 간선 배열에 저장하기!
						if (cur >= 2)
							G.add(new Edge(i, map[nx][ny], cur));
					}
				}
		}
	}

	public static void makeSet() {
		for (int i = 0; i < p.length; i++) {
			p[i] = i;
			rank[i] = 0;
		}
	}

	public static int findSet(int x) {
		if (p[x] == x)
			return x;
		else {
			p[x] = findSet(p[x]);
			return p[x];
		}
	}

	public static void link(int px, int py) {
		if (rank[px] > rank[py])
			p[py] = px;
		else {
			p[px] = py;
			if (rank[px] == rank[py])
				rank[py]++;
		}
	}

	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static boolean isIn(int i, int j) {
		return i >= 0 && i < N && j >= 0 && j < M;
	}

	public static class Edge implements Comparable<Edge> {
		int s;
		int e;
		int val;

		public Edge(int s, int e, int val) {
			this.s = s;
			this.e = e;
			this.val = val;
		}

		@Override
		public int compareTo(Edge o) {
			return this.val - o.val;
		}
	}
}
