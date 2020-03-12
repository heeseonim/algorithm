import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1717_집합의표현 {
	public static int[] p;
	public static int[] rank;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 정점의 개수
		int m = Integer.parseInt(st.nextToken()); // 명령문의 개수

		p = new int[n + 2];
		rank = new int[n + 2];
		for (int i = 0; i < n + 2; i++) {
			makeSet(i);
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int com = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			// 0 이면 합하고
			if (com == 0) {
				union(x, y);
			} else { // 1 이면 부모 확인하여 같은 집합인지 확인
				if (findSet(x) == findSet(y)) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
		}

	}

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

}
