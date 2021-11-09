import java.io.*;
import java.util.*;

// 누적합 Prefix Sum
// 아래, 오른쪽으로 누적합을 dp 배열에 저장
// 2차원 배열을 면적을 구하듯이 계산하여 합을 구함
public class Main {
	public static int N, M, K;
	public static int[][] arr;
	public static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][M + 1];
		dp = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		init();

		K = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < K; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			System.out.println(calc(i, j, x, y));
		}
	}

	// dp 에 누적합 세팅
	public static void init() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				dp[i][j] = arr[i][j] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
			}
		}
	}

	// 면적 (누적합) 구하기
	public static int calc(int i, int j, int x, int y) {
		return dp[x][y] - dp[x][j - 1] - dp[i - 1][y] + dp[i - 1][j - 1];
	}
}
