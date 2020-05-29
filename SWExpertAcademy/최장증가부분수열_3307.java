import java.io.*;
import java.util.*;

public class 최장증가부분수열_3307 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			int[] LIS = new int[N];
			int max = Integer.MIN_VALUE;

			for (int i = 0; i < LIS.length; i++) {
				LIS[i] = 1; // 현재 원소로 만들 수 있는 부분수열의 길이는 1
				for (int j = 0; j < i; j++) { // 앞의 원소들을 탐색
					if (arr[i] > arr[j] && LIS[j] + 1 > LIS[i]) { // 현재 나보다 작은 값을 가지고 있고
						// 앞의 원소의 최장증가수열 + 현재 원소 > 내가 가지고 있는 최장증가수열의 길이 라면 값 갱신
						LIS[i] = LIS[j] + 1;
					}
				}
				max = max < LIS[i] ? LIS[i] : max;
			}

			StringBuilder sb = new StringBuilder();
			sb.append('#').append(tc).append(' ').append(max);
			System.out.println(sb.toString());
		}
	}
}