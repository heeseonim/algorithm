import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_2156 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] wine = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}

		long[] dp = new long[n + 1];
		dp[1] = wine[1];
		if (n >= 2) // * 입력으로 1이 들어왔을 때 주의하기! *
			dp[2] = wine[2] + dp[1];

		for (int i = 3; i < dp.length; i++) {
			dp[i] = Math.max(dp[i - 1], Math.max(wine[i] + dp[i - 2], wine[i] + wine[i - 1] + dp[i - 3]));
		}

		System.out.println(dp[n]);
	}
}