package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_3289_서로소집합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			p = new int[n];
			rank = new int[n];

			for (int i = 0; i < n; i++) {
				p[i] = i;
			}

			StringBuilder sb = new StringBuilder();
			sb.append('#').append(tc).append(' ');
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int com = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken()) - 1;

				if (com == 0) {
					union(x, y);
				} else {
					if (p(x) == p(y))
						sb.append(1);
					else
						sb.append(0);
				}
			}

			System.out.println(sb);
		}
	}

	public static int[] p;
	public static int[] rank;

	public static void union(int x, int y) {
		int px = p(x);
		int py = p(y);

		if (px != py)
			link(px, py);
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

	public static int p(int x) {
		if (p[x] == x)
			return x;
		else {
			p[x] = p(p[x]);
			return p[x];
		}
	}
}
