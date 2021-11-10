import java.io.*;
import java.util.*;

// dfs, dp
public class Main {
	public static int n, max;
	public static int[][] arr; // 대나무 값 저장
	public static int[][] dp; // 칸 수 저장
	public static int[][] dir = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// dfs 실행 및 최댓값 저장
				max = Math.max(max, dfs(i, j));
			}
		}
		System.out.println(max);
	}

	public static int dfs(int x, int y) {
		if (dp[x][y] > 0) // 이미 세팅되어 있다면 리턴
			return dp[x][y];

		dp[x][y] = 1; // 초기값은 현재 칸 수 1

		for (int i = 0; i < dir.length; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];
			// 범위 넘어가거나, 대나무가 많지 않으면 continue
			if (!isIn(nx, ny) || arr[x][y] >= arr[nx][ny])
				continue;
			// 현재 가지고 있는 값과 탐색하여 나온 칸 수에 +1 한 값 중 큰 값으로 저장
			dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
		}

		return dp[x][y];
	}

	public static boolean isIn(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}
}
