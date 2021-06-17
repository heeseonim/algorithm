import java.io.*;
import java.util.*;

public class M4_06_순환회로검사 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int[] p, rank;

	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		p = new int[N];
		rank = new int[N];
		int[][] map = new int[N][N];
		makeSet();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		// 오른쪽만 보기
		for (int i = 0; i < N; i++)
			for (int j = i; j < N; j++)
				if (map[i][j] == 1)
					if (!union(i, j)) {
						System.out.println("WARNING");
						return;
					}

		System.out.println("STABLE");
	}

	public static void makeSet() {
		for (int i = 0; i < p.length; i++) {
			p[i] = i;
		}
	}

	public static int findSet(int x) {
		if (p[x] == x)
			return x;
		else {
			p[x] = findSet(p[x]);
			return p[x];
		}
	}

	public static boolean union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		if (px != py) {
			link(px, py);
			return true;
		} else
			return false;
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
