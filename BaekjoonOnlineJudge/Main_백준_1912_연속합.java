import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1912 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] num = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		long[] dp = new long[n + 1];
		dp[1] = num[1];

		long max = dp[1];
		for (int i = 2; i <= n; i++) {
			dp[i] = Math.max(num[i], num[i] + dp[i - 1]);
			if (max < dp[i])
				max = dp[i];
		}
		
		System.out.println(max);
	}
}
