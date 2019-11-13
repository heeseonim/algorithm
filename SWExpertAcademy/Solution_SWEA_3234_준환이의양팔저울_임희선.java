package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_3234_준환이의양팔저울_임희선 {
	public static int N, answer;
	public static int[] stone;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 추의 개수
			stone = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				stone[i] = Integer.parseInt(st.nextToken());
			}

			answer = 0;
			perm(N, 0);

			StringBuilder sb = new StringBuilder();
			sb.append('#').append(tc).append(' ').append(answer);
			System.out.println(sb);
		}

	}

	public static void perm(int n, int k) {
		if (k == n) {
			choice(0, 0, 0);
		}
		for (int i = k; i < n; i++) {
			int temp = stone[k];
			stone[k] = stone[i];
			stone[i] = temp;
			perm(n, k + 1);
			temp = stone[k];
			stone[k] = stone[i];
			stone[i] = temp;
		}
	}

	public static void choice(int cnt, int left, int right) {
		if (cnt > N)
			return;

		if (cnt == N && left >= right) {
			answer++;
			return;
		}

		if (left >= right) {
			choice(cnt + 1, left, right + stone[cnt]);
			choice(cnt + 1, left + stone[cnt], right);
		}
	}

}
