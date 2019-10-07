import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1263 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 사람 수
			int M = Integer.MAX_VALUE;
			int[][] map = new int[N][N]; // 인접행렬
			int[] CC = new int[N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (i != j && map[i][j] == 0) {
						map[i][j] = M;
					}
				}
			}

			for (int k = 0; k < N; k++) { // 거쳐가는 정점 검사
				for (int i = 0; i < N; i++) { // 출발 정점
					if (k == i)
						continue;
					for (int j = 0; j < N; j++) { // 도착 정점
						if (k == j || i == j)
							continue;
						// 거쳐가는 정점이 도착정점과 연결되어 있고, 더한 값이 현재의 값보다 작을 때 업데이트
						if(map[i][k] != M && map[k][j] != M && map[i][j] > map[i][k] + map[k][j]) {
							map[i][j] = map[i][k] + map[k][j];
						}
					}
				}
			}

			int result = M;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					CC[i] += map[i][j];
				}
				result = Math.min(result, CC[i]);
			}
			

			System.out.println("#" + tc + " " + result);

		}
	}
}
