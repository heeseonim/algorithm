package _PRACTICE;

import java.util.*;

public class 지형이동 {
	public static int N, M, number, h;
	public static boolean[][] visited;
	public static List<List<Point>> island;
	public static int[][] copy; // 높이를 저장할 배열
	public static int[][] map; // 섬의 번호를 붙여 저장할 배열
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static int[] p;
	public static int[] rank;
	public static List<Edge> G;

	public static void main(String[] args) {
		System.out.println(
				solution(new int[][] { { 1, 4, 8, 10 }, { 5, 5, 5, 5 }, { 10, 10, 10, 10 }, { 10, 10, 10, 20 } }, 3));
	}

	public static int solution(int[][] land, int height) {
		N = land.length;
		M = land[0].length;
		h = height;

		copy = new int[N][M];
		for (int i = 0; i < N; i++) {
			copy[i] = land[i].clone();
		}

		map = new int[N][M];
		visited = new boolean[N][M];
		island = new ArrayList<List<Point>>();

		number = 1;
		for (int i = 0; i < land.length; i++) {
			for (int j = 0; j < land[i].length; j++) {
				if (!visited[i][j]) {
					bfs(i, j);
					number++;
				}
			}
		}

		G = new ArrayList<>();
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
				if (cnt == number - 2) // 간선의 개수 = 섬 개수 - 1 (number++ 때문에 하나 더 빼줌)
					break;
			}
		}

		return answer;
	}

	public static void bfs(int r, int c) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(r, c));
		map[r][c] = number;
		visited[r][c] = true;
		List<Point> list = new ArrayList<>();
		list.add(new Point(r, c));

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			for (int i = 0; i < dir.length; i++) {
				int nr = p.x + dir[i][0];
				int nc = p.y + dir[i][1];

				if (!isIn(nr, nc) || visited[nr][nc] || Math.abs(copy[p.x][p.y] - copy[nr][nc]) > h)
					continue;

				visited[nr][nc] = true;
				map[nr][nc] = number;
				queue.add(new Point(nr, nc));
				list.add(new Point(nr, nc));
			}
		}

		island.add(list);
	}

	public static void makeG() {
		for (int i = 0; i < island.size(); i++)
			if (island.get(i) != null) {
				for (int j = 0; j < island.get(i).size(); j++) {
					Point p = island.get(i).get(j);
					for (int k = 0; k < dir.length; k++) {
						int nr = p.x + dir[k][0];
						int nc = p.y + dir[k][1];

						if (!isIn(nr, nc) || map[nr][nc] == (i+1))
							continue;
						
						G.add(new Edge(i + 1, map[nr][nc], Math.abs(copy[nr][nc] - copy[p.x][p.y])));
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
			p[py] = px; // py 의 부모는 px
		else {
			p[px] = py; // px 의 부모는 py
			if (rank[px] == rank[py])
				rank[py]++;
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	public static class Point { // 좌표
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class Edge implements Comparable<Edge> { // 간선
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
			return this.val - o.val; // 오름차순 정렬
		}

	}
}
