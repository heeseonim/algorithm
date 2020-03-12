import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1978 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int answer = N;

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] num = new int[N];
		for (int i = 0; i < num.length; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < num.length; i++) {
			if (num[i] == 1) {
				answer--;
				continue;
			}
			for (int j = 2; j < num[i]; j++) {
				if (num[i] % j == 0) {
					answer--;
					break;
				}
			}
		}
		
		System.out.println(answer);

	}
}
