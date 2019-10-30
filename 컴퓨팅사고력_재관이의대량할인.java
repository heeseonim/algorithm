import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_4050 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] clothes = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				clothes[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(clothes);

			int answer = 0;
			if (N >= 3) { // 옷이 세벌 이상
				for (int i = clothes.length - 1; i >= 0; i -= 3) {
					if (i - 2 >= 0) { // 세 벌이 존재
						answer += clothes[i] + clothes[i - 1];
					} else {
						for (int j = i; j >= 0; j--) {
							answer += clothes[j];
						}
					}
				}
			} else {
				for (int i = clothes.length-1; i >= 0; i--) {
					answer += clothes[i];
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
	}
}
