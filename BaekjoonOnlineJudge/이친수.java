import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_2193 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] num = new long[N + 1];
		num[1] = 1;
		if (N > 1)
			num[2] = 1;
		// num[n] = num[n-1] + num[n-2]

		for (int i = 3; i <= N; i++) {
			num[i] = num[i - 1] + num[i - 2];
		}

		System.out.println(num[N]);
	}
}
