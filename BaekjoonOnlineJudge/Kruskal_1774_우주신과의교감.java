import java.io.*;
import java.util.*;

public class Kruskal_1774_우주신과의교감 {
	public static int N, M;
	public static Node[] space;
	public static int[] p;
	public static int[] rank;
	public static List<Edge> G;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		space = new Node[N];
		p = new int[N];
		rank = new int[N];
		G = new ArrayList<Edge>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			space[i] = new Node(x, y);
		}

		init();

		// 이미 연결된 통로 연결해주기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;

			int px = find(x);
			int py = find(y);
			if (px != py)
				link(px, py);
		}

		// 두 점 사이의 거리 계산해서 간선 리스트에 넣어줌
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				G.add(new Edge(i, j, distance(space[i], space[j])));
			}
		}

		// kruskal
		Collections.sort(G);
		double result = 0.00;
		for (Edge e : G) {
			int px = find(e.s);
			int py = find(e.e);
			if (px != py) {
				link(px, py);
				result += e.val;
			}
		}
		
		System.out.println(String.format("%.2f", result));
	}

	// 두 점 사이의 거리
	// 루트((x좌표차이)^2 + (y좌표차이)^2)
	public static double distance(Node n1, Node n2) {
		return Math.sqrt(Math.pow(n1.x - n2.x, 2) + Math.pow(n1.y - n2.y, 2));
	}

	public static void init() {
		for (int i = 0; i < N; i++) {
			p[i] = i;
		}
	}

	public static int find(int x) {
		if (p[x] == x)
			return p[x];
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

	public static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class Edge implements Comparable<Edge> {
		int s, e;
		double val;

		public Edge(int s, int e, double val) {
			this.s = s;
			this.e = e;
			this.val = val;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(val, o.val);
		}
	}
}
