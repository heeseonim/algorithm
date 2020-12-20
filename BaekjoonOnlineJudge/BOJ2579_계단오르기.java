import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2579_계단오르기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] stairs = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}

		int[] max_value = new int[N + 1];
		max_value[1] = stairs[1];

		if (N > 1)
			max_value[2] = stairs[1] + stairs[2];
		for (int i = 3; i <= N; i++)
			max_value[i] = Math.max(max_value[i - 2] + stairs[i], max_value[i - 3] + stairs[i - 1] + stairs[i]);

		System.out.println(max_value[N]);
	}
}