import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14501 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int time = Integer.parseInt(st.nextToken());
			int pay = Integer.parseInt(st.nextToken());

			dp[i + 1] = Math.max(dp[i], dp[i + 1]); // 최댓값 갱신
			if (i + time <= N)
				dp[i + time] = Math.max(dp[i + time], dp[i] + pay); // 하지 않는 경우, 하는 경우
		}
		
		System.out.println(dp[N]);
	}
}
