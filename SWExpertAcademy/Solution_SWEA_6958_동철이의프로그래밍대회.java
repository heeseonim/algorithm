import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_6958_동철이의프로그래밍대회 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] check = new int[N][M];
			int[] count = new int[M+1];
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int sum = 0;
				for (int j = 0; j < M; j++) {
					int k = Integer.parseInt(st.nextToken());
					sum += k;
				}
				count[sum]++; // 사람 수 증가
				max = Math.max(max, sum); // 푼 문제의 수의 최대값 저장
			}
			System.out.println("#" + tc + " " + count[max] + " " + max);
		}
	}
}
