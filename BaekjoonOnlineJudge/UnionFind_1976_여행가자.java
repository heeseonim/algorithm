import java.io.*;
import java.util.*;

public class UnionFind_1976_여행가자 {
	public static int N, M;
	public static int[] p;
	public static int[] rank;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		p = new int[N];
		rank = new int[N];
		
		init();
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int px = find(i);
			for (int j = 0; j < N; j++) {
				// 1인 요소가 있으면, 두 지점 연결
				if (st.nextToken().equals("1")) {
					int py = find(j);
					if (px != py)
						link(px, py);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		// 맨 처음 도시의 루트 찾기
		int root = find(Integer.parseInt(st.nextToken()) - 1);
		for (int i = 1; i < M; i++) {
			int num = Integer.parseInt(st.nextToken()) - 1;
			// 같은 루트에 속하지 않으면, 여행불가
			if (root != find(num)) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
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
