import java.io.*;
import java.util.*;

public class M5_01_알뜰기차여행 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 노드
		int T = Integer.parseInt(st.nextToken()); // 간선

		int[][] G = new int[N][N];
		int m = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			Arrays.fill(G[i], m); // m으로 세팅
			G[i][i] = 0;
		}

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			G[s][e] = val;
		}

		int[] D = G[0]; // 0번 노드에서 출발
		boolean[] used = new boolean[N];

		for (int i = 0; i < G.length; i++) {
			int minIndex = -1;
			int min = m;
			// minIndex 구하기
			for (int j = 0; j < D.length; j++) {
				if (!used[j] && min > D[j]) {
					minIndex = j;
					min = D[j];
				}
			}

			if (minIndex == -1)
				continue;
			used[minIndex] = true; // 사용표시

			// minIndex 로 부터 연결될 수 있는 지점과 연결하여 값 갱신
			for (int j = 0; j < D.length; j++) {
				if (!used[j] && G[minIndex][j] != m && D[j] > D[minIndex] + G[minIndex][j])
					D[j] = D[minIndex] + G[minIndex][j];
			}
		}

		System.out.println(D[N - 1] == m ? "impossible" : D[N - 1]);
	}
}
