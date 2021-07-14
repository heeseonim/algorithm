import java.io.*;
import java.util.*;

public class MST_BOJ_1197_최소스패닝트리 {
	public static int[] p;
	public static int[] rank;
	public static int V, E;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		Edge[] G = new Edge[E];
		p = new int[V];
		rank = new int[V];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;
			int C = Integer.parseInt(st.nextToken());
			G[i] = new Edge(A, B, C);
		}
		Arrays.sort(G);
		
		init();

		int cnt = 0;
		int sum = 0;
		for (int i = 0; i < G.length; i++) {
			Edge cur = G[i];
			int px = findP(cur.s);
			int py = findP(cur.e);

			if (px != py) {
				link(px, py);
				sum += cur.val;
				cnt++;
			}

			if (cnt == V - 1)
				break;
		}

		System.out.println(sum);
	}

	public static void init() {
		for (int i = 0; i < V; i++) {
			p[i] = i;
		}
	}
	
	public static int findP(int x) {
		if (p[x] == x)
			return x;
		else {
			p[x] = findP(p[x]);
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

	public static class Edge implements Comparable<Edge> {
		int s, e, val;

		public Edge(int s, int e, int val) {
			super();
			this.s = s;
			this.e = e;
			this.val = val;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.val - o.val;
		}

	}
}
