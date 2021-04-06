import java.io.*;
import java.util.*;

public class Kruskal_BOJ_1647_도시분할계획 {
	public static int[] p;
	public static int[] rank;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		p = new int[V];
		rank = new int[V];
		Edge[] G = new Edge[E];

		for (int i = 0; i < V; i++) {
			makeSet(i);
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int val = Integer.parseInt(st.nextToken());
			G[i] = new Edge(s, e, val);
		}

		Arrays.sort(G);

		int mst = 0;
		int cnt = 0;
		int lastVal = 0;

		for (int i = 0; i < E; i++) {
			Edge e = G[i];
			int px = findSet(e.s);
			int py = findSet(e.e);
			if (px != py) {
				link(px, py);
				mst += e.val;
				cnt++;
				if (cnt == V - 1) {
					lastVal = e.val;
					break;
				}
			}
		}

		System.out.println(mst - lastVal);
	}

	public static class Edge implements Comparable<Edge> {
		int s, e, val;

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

	public static void makeSet(int x) {
		p[x] = x;
		rank[x] = 0;
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
}
