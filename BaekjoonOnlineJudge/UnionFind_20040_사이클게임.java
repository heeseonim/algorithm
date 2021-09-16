import java.io.*;
import java.util.*;

public class UnionFind_20040_사이클게임 {
	public static int N, M;
	public static int[] p;
	public static int[] rank;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		p = new int[N];
		rank = new int[N];
		init();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int px = find(x);
			int py = find(y);

			if (px == py) {
				System.out.println(i + 1);
				return;
			} else {
				link(px, py);
			}
		}

		System.out.println(0);

	}

	public static void init() {
		for (int i = 0; i < N; i++) {
			p[i] = i;
		}
	}

	public static int find(int x) {
		if (x == p[x])
			return x;
		else
			return p[x] = find(p[x]);
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
