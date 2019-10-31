import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_SWEA_6719_성수의프로그래밍강좌시청 {
	// swea 6719 성수의 프로그래밍 강좌 시청
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 총 강좌의 개수
			int K = Integer.parseInt(st.nextToken()); // 선택할 강좌의 개수
			
			int[] M = new int[N]; // 각 강좌의 수준 값 저장
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				M[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(M); // 오름차순으로 정렬
			
			double A = 0; // 성수의 실력
			for (int i = N-K; i < N; i++) {
				A = (A + M[i])/2;
			}
			System.out.printf("#%d %.6f\n", tc, A);
		}
	}
}
