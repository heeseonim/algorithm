import java.io.*;
import java.util.StringTokenizer;

public class BOJ_1717_집합의표현 {
	public static int N;
	public static int[] p;
	public static int[] rank;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		p = new int[N + 2];
		rank = new int[N + 2];
		makeSet();


		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int type = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int px = find(x);
			int py = find(y);
			if (type == 0) {
				if (px != py)
					link(px, py);
			} else {
				if (px == py)
					System.out.println("YES");
				else
					System.out.println("NO");
			}
		}

	}

	public static void makeSet() {
		for (int i = 0; i < N + 2; i++) {
			p[i] = i;
		}
	}

	public static int find(int x) {
		if (p[x] == x)
			return x;
		else {
			p[x] = find(p[x]);
			return p[x];
		}
	}

	public static void link(int px, int py) {
		if (rank[px] > rank[py]) {
			p[py] = px;
			rank[px]++;
		} else {
			p[px] = py;
			rank[py]++;
		}
	}
}
