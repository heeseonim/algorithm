import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1463 {
	public static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		result = Integer.MAX_VALUE;
		check(0, N);
		System.out.println(result);
	}

	// Top-down
	public static void check(int level, int cur) {
		if (level >= result)
			return; // 가지치기

		if (cur == 1) {
			if (level < result)
				result = level;
			return;
		}

		if (cur % 3 == 0)
			check(level + 1, cur / 3);
		else if (cur % 2 == 0)
			check(level + 1, cur / 2);

		check(level + 1, cur - 1);
	}
}
