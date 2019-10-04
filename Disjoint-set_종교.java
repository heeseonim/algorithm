import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_정올_1863_종교 {
	public static int n, m;
	public static int[] parent;
	public static int[] rank;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		parent = new int[n];
		rank = new int[n];
		m = Integer.parseInt(st.nextToken());
		makeP();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			union(x, y);
		}

		int result = 0;
		for (int i = 0; i < n; i++) {
			if (parent[i] == i) { // 자기 자신이 부모인 것
				result++;
			}
		}

		if (m == 0) { // 같은 종교 가진 학생이 없음
			System.out.println(n);
		} else {
			System.out.println(result);
		}

	} // end of main

	// 부모 초기화
	public static void makeP() {
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	// 부모 찾는 함수
	public static int findP(int x) {
		if (parent[x] == x) { // 부모가 자기 자신이면
			return x; // 자기 자신을 반환
		} else { // 부모가 자기 자신이 아니면
			parent[x] = findP(parent[x]); // 부모의 부모를 찾기
			return parent[x]; // 찾은 부모를 반환
		}
	}

	// 부모가 같은지 검사 - 짝이면 같은 종교이므로 부모가 같아야 한다.
	public static void union(int x, int y) {
		int px = findP(x);
		int py = findP(y);
		if (px != py) {
			link(px, py);
		}
	}

	// 같은 종교라면 연결
	public static void link(int px, int py) {
		if (rank[px] > rank[py]) {
			parent[py] = px;
		} else {
			parent[px] = py;
			if (rank[px] == rank[py]) {
				rank[py]++;
			}
		}
	}
} // end of class
