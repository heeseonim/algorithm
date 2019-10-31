import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5607_조합 {
	// swea 5607 Professional 조합
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int P = 1234567891;

			long[] factorial = new long[N + 1]; // 1~N 값 저장
			factorial[0] = 1;
			factorial[1] = 1;

			// factorial 값 미리 구하기
			for (int i = 2; i <= N; i++) {
				factorial[i] = (i * factorial[i - 1]) % P; // 나머지연산 후 저장
			}

			// 분모 구하기
			long denominator = (factorial[R] * factorial[N - R]) % P;

			// 분모의 역원 구하기
			long index = P - 2;
			long result = 1; // 저장될 값
			
			// 분할정복
			while (index > 0) {
				if (index % 2 == 1) {
					result *= denominator;
					result %= P;
				}
				denominator = (denominator * denominator) % P;
				index /= 2;
			}
			
			long answer = ((factorial[N] % P) * (result % P)) % P;
			System.out.println("#" + tc + " " + answer);

		}
	}
}
