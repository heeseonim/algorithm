package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_8104_조만들기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[] group = new int[K]; // 조의 개수 1~K 사용

			int n = 0;
			while (true) {
//				0K+1 (0+1)K 순서대로
//				1K+1 (1+1)K 역순
//				2K+1 (2+1)K 순서대로
				int index = 0;
				for (int i = (n * K) + 1; i <= (n + 1) * K; i++) {
					if (n % 2 == 0) {
						group[index] += i;
					} else if (n % 2 == 1) {
						group[K - index - 1] += i;
					}
					index++;
				}
				n++;
				if (n == N) {
					break;
				}
			}

			System.out.print("#" + tc + " ");
			for (int student : group) {
				System.out.print(student + " ");
			}
			System.out.println();

		}

	}

}
