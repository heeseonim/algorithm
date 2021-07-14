import java.io.*;
import java.util.*;

public class MST_BOJ_2887_행성터널 {
	static int N;
	static int[] p;
	static int[] rank;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		p = new int[N];
		rank = new int[N];
		Point[] arr = new Point[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			arr[i] = new Point(i, x, y, z);
		}

		List<Edge> G = new ArrayList<>();
		// x, y, z 를 기준으로 정렬
		Arrays.sort(arr, (p1, p2) -> p1.x - p2.x);
		for (int i = 0; i < N - 1; i++) {
			int val = Math.abs(arr[i].x - arr[i + 1].x);
			G.add(new Edge(arr[i].num, arr[i + 1].num, val));
		}
		Arrays.sort(arr, (p1, p2) -> p1.y - p2.y);
		for (int i = 0; i < N - 1; i++) {
			int val = Math.abs(arr[i].y - arr[i + 1].y);
			G.add(new Edge(arr[i].num, arr[i + 1].num, val));
		}
		Arrays.sort(arr, (p1, p2) -> p1.z - p2.z);
		for (int i = 0; i < N - 1; i++) {
			int val = Math.abs(arr[i].z - arr[i + 1].z);
			G.add(new Edge(arr[i].num, arr[i + 1].num, val));
		}

		makeSet();

		Collections.sort(G);
		int size = 0;
		int answer = 0;
		for (int i = 0; i < G.size(); i++) {
			Edge cur = G.get(i);
			int px = find(cur.s);
			int py = find(cur.e);
			if (px != py) {
				link(px, py);
				answer += cur.val;
				size++;
			}
			if (size == N-1)
				break;
		}
		System.out.println(answer);
	}

	static void makeSet() {
		for (int i = 0; i < N; i++) {
			p[i] = i;
		}
	}

	static int find(int x) {
		if (p[x] == x)
			return x;
		else {
			p[x] = find(p[x]);
			return p[x];
		}
	}

	static void link(int px, int py) {
		if (rank[px] > rank[py]) {
			p[py] = px;
		} else {
			p[px] = py;
			if (rank[px] == rank[py])
				rank[py]++;
		}
	}

	public static class Point {
		int num, x, y, z;

		public Point(int num, int x, int y, int z) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.z = z;
		}
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
}
