import java.util.Arrays;
import java.util.Scanner;

public class Solution_3124_최소스패닝트리 {
	public static class Edge implements Comparable<Edge> {
		int a;
		int b;
		int val;

		public Edge(int a, int b, int val) {
			this.a = a;
			this.b = b;
			this.val = val;
		}

		@Override
		public int compareTo(Edge o) {
			return this.val - o.val;
		}

	}

	public static int[] p;
	public static int[] rank;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int V = sc.nextInt();
			int E = sc.nextInt();

			Edge[] G = new Edge[E];
			for (int i = 0; i < E; i++) {
				G[i] = new Edge(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt());
			}
			Arrays.sort(G);

			p = new int[V];
			rank = new int[V];
			for (int i = 0; i < V; i++) {
				makeSet(i);
			}

			long mst = 0L;
			int cnt = 0;

			for (int i = 0; i < E; i++) {
				Edge e = G[i];
				int px = findSet(e.a);
				int py = findSet(e.b);
				if (px != py) {
					union(px, py);
					mst += e.val;
					cnt++;
					if (cnt == V - 1) {
						break;
					}
				}

			}
			System.out.println("#" + tc + " " + mst);
		}

	} // end of main

	public static void makeSet(int x) {
		p[x] = x;
		rank[x] = 0;
	}

	public static int findSet(int x) {
		if (p[x] == x) {
			return x;
		} else {
			p[x] = findSet(p[x]);
			return p[x];
		}
	}

	public static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		if (px != py) {
			link(px, py);
		}
	}

	public static void link(int px, int py) {
		if (rank[px] > rank[py]) {
			p[py] = px;
		} else {
			p[px] = py;
			if (rank[px] == rank[py]) {
				rank[py]++;
			}
		}
	}
} // end of class
