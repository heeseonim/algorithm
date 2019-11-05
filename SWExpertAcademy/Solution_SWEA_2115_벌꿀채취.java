import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_2115_벌꿀채취 {
	public static int[][] map;
	public static boolean[][] visited;
	public static int N, M, C, result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int max = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - M + 1; j++) {
					max = Math.max(max, choice(i, j)); // 첫번째 일꾼이 꿀통 선택
				}
			}

			System.out.println("#" + tc + " " + max);
		}
	} // end of main

	public static int choice(int x, int y) {
		// 물통 개수만큼 visited 처리
		for (int i = 0; i < M; i++) {
			visited[x][y + i] = true;
		}
		result = 0;
		calc(x, y, 0, 0, 0);
		int costA = result;

		// 두번째 일꾼이 꿀통 선택
		int costB = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - M + 1; j++) {
				if (visited[i][j])
					continue; // 첫번째 일꾼이 선택한 꿀통이면 넘어감
				result = 0;
				calc(i, j, 0, 0, 0);
				costB = Math.max(result, costB);
			}
		}

		return costA + costB;

	}

	public static void calc(int x, int y, int cnt, int sum, int total) {
		// 꿀통의 합이 최대로 가능한 양보다 많으면 계산하지 않음
		if (sum > C) return;
		result = Math.max(result, total);
		// 물통을 다 선택했다면 return
		if (cnt == M) return;
		
		calc(x, y + 1, cnt + 1, sum + map[x][y], total+map[x][y]*map[x][y]);
		calc(x, y + 1, cnt + 1, sum, total);
	}
} // end of class
