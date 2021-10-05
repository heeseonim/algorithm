import java.io.*;
import java.util.*;

public class DP_1520_내리막길 {
	public static int M, N;
	public static int[][] map;
	public static int[][] dp;
	public static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		dp = new int[M][N];
		for (int i = 0; i < M; i++)
			Arrays.fill(dp[i], -1); // -1로 세팅

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(dfs(0, 0));
		br.close();
	}

	public static int dfs(int x, int y) {
		if (x == M - 1 && y == N - 1)
			return 1; // 오른쪽 아래에 도착했다면 경로 1 반환
		else if (dp[x][y] == -1) {// 초기값이라면 dfs 실행
			dp[x][y] = 0; // 0으로 초기화

			for (int i = 0; i < dir.length; i++) {
				int nx = x + dir[i][0];
				int ny = y + dir[i][1];

				if (!isIn(nx, ny) || map[nx][ny] >= map[x][y])
					continue;

				dp[x][y] += dfs(nx, ny); // dfs 실행하여 반환값 (경로 수) 추가
			}
		}

		return dp[x][y]; // 초기값 아니라면 반환
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < M && c >= 0 && c < N;
	}
}
