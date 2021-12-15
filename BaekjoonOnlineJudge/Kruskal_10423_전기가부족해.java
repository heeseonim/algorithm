import java.io.*;
import java.util.*;

/*
 * 1. 발전소의 부모노드를 0으로 설정 - 0인 가상노드
 * 2. 크루스칼
 * 3. 모든 노드가 0이 되면 종료
 * -> 발전소끼리 이미 부모노드가 같기 때문에 연결되지 않으며,
 * -> 모든 노드가 연결될 필요없이 단순하게 부모노드가 0이 되도록 한다.
 * */
public class Main {
	public static int N, M, K;
	public static List<Point> G;
	public static int[] p;
	public static int[] rank;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		G = new ArrayList<Main.Point>();
		p = new int[N + 1];
		rank = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; i++) {
			int store = Integer.parseInt(st.nextToken());
			p[store] = 0; // 발전소의 부모노드를 0으로 표시
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			G.add(new Point(u, v, w));
		}

		// kruskal
		Collections.sort(G);
		int sum = 0;
		for (int i = 0; i < G.size(); i++) {
			Point cur = G.get(i);
			int px = findP(cur.u);
			int py = findP(cur.v);
			if (px != py) {
				link(px, py);
				sum += cur.w;
			}
			if (check()) // 부모노드가 모두 0이라면 종료
				break;
		}

		bw.write(String.valueOf(sum));
		bw.flush();
		bw.close();
		br.close();
	}

	public static boolean check() {
		for (int i = 1; i <= N; i++)
			if (p[i] != 0) // 부모노드가 0이 아닌 경우가 있다면
				return false;
		return true;
	}

	public static int findP(int x) {
		if (p[x] == x)
			return x;
		else {
			return p[x] = findP(p[x]);
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

	public static class Point implements Comparable<Point> {
		int u, v, w;

		public Point(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Point o) {
			return this.w - o.w;
		}
	}
}
