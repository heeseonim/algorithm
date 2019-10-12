import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1922 {
	public static int[] p;
	public static int[] rank;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		Edge[] G = new Edge[E]; // 간선 저장 배열
		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			G[i] = new Edge(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(G); // 가중치를 기준으로 정렬

		p = new int[V];
		rank = new int[V];
		for (int i = 0; i < V; i++) {
			makeSet(i);
		}

		int mst = 0;
		int cnt = 0;

		for (int i = 0; i < E; i++) {
			Edge e = G[i]; // 작은 가중치의 간선부터 꺼냄
			int px = findSet(e.a); // 대표자를 찾음
			int py = findSet(e.b);
			if (px != py) { // 사이클이 생기면 안됨
				union(px, py); // 해당 정점 연결
				mst += e.val; // 가중치 더해주기
				cnt++; // 연결된 간선 수
				if (cnt == V - 1) {
					break;
				}
			}
		}

		System.out.println(mst);

	} // end of main

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

	public static void makeSet(int x) {
		p[x] = x; // 자기 자신을 부모로 표시
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
		if (px != py) { // 서로 다른 집합일 때 합치기
			link(px, py);
		}
	}

	public static void link(int px, int py) {
		if (rank[px] > rank[py]) {
			p[py] = px; // 작은 쪽을 큰 쪽에 붙임
		} else {
			p[px] = py;
			if (rank[px] == rank[py]) {
				rank[py]++;
			}
		}
	}
}
