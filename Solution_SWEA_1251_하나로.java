import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_SWEA_1251_하나로 {
	public static class Edge implements Comparable<Edge> {
		int a, b;
		long val;

		public Edge(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.val, o.val);
		}

	}

	public static int[] p;
	public static int[] rank;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 섬의 개수
			int[] x = new int[N]; // x좌표
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < x.length; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			int[] y = new int[N]; // y좌표
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < y.length; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}
			double echo = Double.parseDouble(br.readLine()); // 환경부담금

			int V = N; // 섬의 개수 = 노드 개수
			int E = N * N; // 모든 경우 확인 위해

			Edge[] Node = new Edge[N]; // 노드 배열
			Edge[] G = new Edge[E]; // 간선배열
			for (int i = 0; i < N; i++) { // 정점의 개수만큼 노드 생성
				Node[i] = new Edge(x[i], y[i]);
			}

			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					G[count] = new Edge(i, j);
					G[count].val = (long) (Node[i].a - Node[j].a) * (Node[i].a - Node[j].a)
							+ (long) (Node[i].b - Node[j].b) * (Node[i].b - Node[j].b);
					count++;
				}
			}

			Arrays.sort(G); // 간선 정렬

			p = new int[V];
			rank = new int[V];
			for (int i = 0; i < V; i++) {
				makeSet(i);
			}

			double mst = 0.0;

			for (int i = 0; i < E; i++) {
				Edge e = G[i];
				int px = findSet(e.a);
				int py = findSet(e.b);
				if (px != py) {
					union(px, py);
					mst += e.val;
				}

			}
			
			mst *= echo;
			System.out.println("#" + tc + " " + Math.round(mst));
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
